import CommandsData.ClientCommandsData
import modul.SingletonObject.mod
import tools.CommandProcessor
import org.koin.core.context.GlobalContext.startKoin
import serializ.Serializer
import tools.input.InputSystem

fun main() {

    startKoin {
        modules(mod)
    }

    val input = InputSystem()

    val commandProcessor: CommandProcessor = CommandProcessor()
    commandProcessor.process(input)
}

//fun main() {
//    val data = ClientCommandsData()
//    val serializer = Serializer()
//
//    data.setName("sdkfsjkjfs")
////    data.setMapData(mutableMapOf("jskdfj" to "sdkjsfsjd", "dskfjs" to "skdj"))
//    val xml = serializer.serialize(data)
//    println(xml)
//    var data2: ClientCommandsData = ClientCommandsData()
//    data2 = serializer.deserialize(xml)
//    println(data2.getMapData())
//}
