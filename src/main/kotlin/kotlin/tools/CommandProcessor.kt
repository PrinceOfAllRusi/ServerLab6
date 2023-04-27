package tools

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import serializ.TimeSerializer
import tools.input.Input
import tools.input.InputFile
import tools.result.Result
import tools.serializ.TimeDeserializer
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.time.LocalDateTime
import transmittedData.SendCommandsData
import CommandsData.ReceiveCommandsData


class CommandProcessor: KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val commandsList: CommandsList by inject()

    fun process(input: Input) {

        var result: Result? = Result(false)
        var mapData: Map<String, Any>?

        var command = ""
        var receiveCommandsData = ReceiveCommandsData() //получаемые от клиента данные

//        var maxId = 0
//        for ( org in orgs ) {
//            maxId = max(maxId, org.getId()!!)
//        }
//        val counter = maxId
//        abstractCommand.setCounter(counter)

        //инициализирую все, что требуется для передачи данных
        var port = 6789
        var host: InetAddress
        val serverSocket = DatagramSocket(port)
        val receivingDataBuffer = ByteArray(65535)
        var sendingDataBuffer = ByteArray(65535)
        val inputPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
        var outputPacket: DatagramPacket
        var receivedData = ""

        //создаю объект, который сериализует данные
        val mapper = XmlMapper()
        val module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, TimeSerializer())
        module.addDeserializer(LocalDateTime::class.java, TimeDeserializer())
        mapper.registerModule(module)
        var xml = ""


        val commandsData = SendCommandsData() //список команд с требуемыми параметрами, который отправляется клиенту
        xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandsData)

        serverSocket.receive(inputPacket)
        sendingDataBuffer = xml.toByteArray()
        port = inputPacket.port
        host = inputPacket.address

        outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        serverSocket.send(outputPacket) // отправляю список команд клиенту

        var receiveMassage = "" //TODO временно

        while ( true ) {

            receiveMassage = "" //TODO временно

            serverSocket.receive(inputPacket)
            xml = String(inputPacket.data, 0, inputPacket.length)

            receiveCommandsData = mapper.readValue<ReceiveCommandsData>(xml)

            command = receiveCommandsData.getName()

            result?.setMessage("")

            if ( !commandsList.containsCommand(command) ) {
                receiveMassage = "Такой команды не существует\n"
            }
            else {
                try {
                    mapData = receiveCommandsData.getMapCommands()
                    result = commandsList.getCommand(command)?.action(mapData)

                } catch ( e: NumberFormatException ) {
                    input.outMsg("Неверные данные\n")
                    if ( input.javaClass == InputFile("").javaClass ) {
                        continue
                    }
                } catch ( e: NullPointerException ) {
                    input.outMsg("Введены не все данные\n")
                }
            }

            receiveMassage = result?.getMessage().toString()

            if (result?.getExit() == true) {
                break
            }

            xml = mapper.writeValueAsString(receiveMassage)

            sendingDataBuffer = xml.toByteArray()
            port = inputPacket.port
            host = inputPacket.address

//            System.out.println(xml)

            outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
            serverSocket.send(outputPacket)

        }

    }
}