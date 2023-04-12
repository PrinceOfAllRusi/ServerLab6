package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.input.Input
import tools.input.InputFile
import tools.result.Result
import java.lang.reflect.Array
import java.net.DatagramPacket

import java.net.DatagramSocket

import java.net.InetAddress




/**
 * Command processor
 *
 * @constructor Create empty Command processor
 */
class CommandProcessor: KoinComponent {

    private val commandsList: CommandsList by inject()

    /**
     * Process
     *
     * @param input
     */
    fun process(input: Input) {

        var result: Result? = Result(false)
        var mapData: Map<String, Any>?

        var command = ""

        var port = 6789
        var host: InetAddress
        val serverSocket = DatagramSocket(port)
        val receivingDataBuffer = ByteArray(1024)
        var sendingDataBuffer = ByteArray(1024)
        val inputPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
        var outputPacket: DatagramPacket
        var receivedData = ""

//        System.out.println("Waiting for a client to connect...")
//        serverSocket.receive(inputPacket)
//        receivedData = String(inputPacket.getData())
//        System.out.println("Sent from the client: " + receivedData)
//        sendingDataBuffer = receivedData.toByteArray()
//        port = inputPacket.port
//        host = inputPacket.address
//
//        outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
//        serverSocket.send(outputPacket)

        while ( true ) {

            System.out.println("Waiting for a client to connect...")
            serverSocket.receive(inputPacket)
            command = String(inputPacket.data, 0, inputPacket.length)

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
                    mapData = commandsList.getType(type)?.processing(input)
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
            if ( result != null ) {
                input.outMsg(result.getMessage())
            }
            if (result?.getExit() == true) {
                break
            }

            sendingDataBuffer = "Done".toByteArray()
            port = inputPacket.port
            host = inputPacket.address

            outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
            serverSocket.send(outputPacket)

        }

    }
}