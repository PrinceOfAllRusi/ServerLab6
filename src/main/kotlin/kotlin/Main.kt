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