import modul.SingletonObject.mod
import tools.CommandProcessor
import org.koin.core.context.GlobalContext.startKoin
import tools.input.InputFile

fun main() {

    startKoin {
        modules(mod)
    }


 //   val convertor = ConversionXMLtoObject()
    val input = InputFile("")
 //   convertor.convert(input)

    val commandProcessor: CommandProcessor = CommandProcessor()
    commandProcessor.process(input)
}

//fun main() {
//    val mapper = XmlMapper()
//    val module = SimpleModule()
//    module.addSerializer(LocalDateTime::class.java, TimeSerializer())
//    module.addDeserializer(LocalDateTime::class.java, TimeDeserializer())
//    mapper.registerModule(module)
//    var xml = ""
//
//    val map1: Map<String, String> = mapOf("one" to "one", "two" to "two")
//
//    System.out.println(map1)
//
//    xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map1)
//
//    System.out.println(xml)
//
//    val map2: Map<String, String> = mapper.readValue<Map<String, String>>(xml)
//
//    System.out.println(map2)
//}