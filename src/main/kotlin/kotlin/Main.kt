import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import modul.SingletonObject.mod
import tools.CommandProcessor
import org.koin.core.context.GlobalContext.startKoin
import serializ.TimeSerializer
import tools.input.InputFile
import tools.result.Result
import tools.serializ.TimeDeserializer
import java.time.LocalDateTime

fun main() {

    startKoin {
        modules(mod)
    }

    val input = InputFile("")

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
//    val result1 = Result(false)
//    result1.setMessage("Message")
//
//    xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result1)
//    val result2 = mapper.readValue<Result>(xml)
//
//    System.out.println(result1.getMessage())
//    System.out.println(xml)
//    System.out.println(result2.getMessage())
//}