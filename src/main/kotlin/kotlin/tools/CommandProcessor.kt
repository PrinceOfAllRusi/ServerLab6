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
import java.lang.Integer.max
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.time.LocalDateTime
import transmittedData.CommandsData


class CommandProcessor: KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val commandsList: CommandsList by inject()

    fun process(input: Input) {

        var result: Result? = Result(false)
        var mapData: Map<String, Any>?

        var command = ""
        var abstractCommand = ConcreteCommand()
        var concreteCommand = ConcreteCommand()

        var maxId = 0
        for ( org in orgs ) {
            maxId = max(maxId, org.getId()!!)
        }
        val counter = maxId
        abstractCommand.setCounter(counter)

        var port = 6789
        var host: InetAddress
        val serverSocket = DatagramSocket(port)
        val receivingDataBuffer = ByteArray(65535)
        var sendingDataBuffer = ByteArray(65535)
        val inputPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
        var outputPacket: DatagramPacket
        var receivedData = ""

        val mapper = XmlMapper()
        val module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, TimeSerializer())
        module.addDeserializer(LocalDateTime::class.java, TimeDeserializer())
        mapper.registerModule(module)
        var xml = ""


        val commandsData = CommandsData()
        serverSocket.receive(inputPacket)
        xml = mapper.writeValueAsString(commandsData)

        sendingDataBuffer = xml.toByteArray()
        port = inputPacket.port
        host = inputPacket.address

        outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        serverSocket.send(outputPacket)


        System.out.println("Waiting for a client to connect...")


        while ( true ) {

            serverSocket.receive(inputPacket)
            xml = String(inputPacket.data, 0, inputPacket.length)

            abstractCommand = mapper.readValue<ConcreteCommand>(xml)
            command = abstractCommand.getName()

            System.out.println(command)

            result?.setMessage("")

            if ( !commandsList.containsCommand(command) ) {
                input.outMsg("Такой команды не существует\n")
            }
            else {
                try {
                    val type = commandsList.getCommand(command)?.getType()
                    if ( type == null ) {
                        continue
                    }
                    mapData = commandsList.getType(type)?.processing(input, abstractCommand)
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

            input.outMsg(result?.getMessage())

            if (result?.getExit() == true) {
                break
            }

            abstractCommand.cleanFields()

            if (result != null) {
                abstractCommand.setMessage(result.getMessage())
            }

            xml = mapper.writeValueAsString(abstractCommand)

            sendingDataBuffer = xml.toByteArray()
            port = inputPacket.port
            host = inputPacket.address

//            System.out.println(xml)

            outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
            serverSocket.send(outputPacket)

        }

    }
}