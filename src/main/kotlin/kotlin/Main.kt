import modul.SingletonObject.mod
import tools.CommandProcessor
import org.koin.core.context.GlobalContext.startKoin
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
//
//    val fields: Map<String, String> = mapOf("one" to " ", "two" to "two", "three" to "three")
//    val map = fields.filterKeys { !it.contains("two") }
//
//    val s = " "
//    println(fields["one"])
//}
