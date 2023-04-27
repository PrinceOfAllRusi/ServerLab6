package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.*
import tools.input.InputFile
import tools.result.Result
import kotlin.collections.ArrayList

class ExecuteScript: AbstractCommand(), KoinComponent {

    private val absoluteWay: ArrayList<String> by inject()
    private val description: String = "считать и исполнить скрипт из указанного файла"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "script" to mapOf<String, String>(
            "title" to "Введите переменную окружения содержащую путь к файлу\n",
            "type" to "String"
        )
    )
    override fun action(data: Map<String, String?>): Result? {

        val s = data["script"].toString()

        val input = InputFile(s)

        val commandProcessor = CommandProcessor()
            commandProcessor.process(input)

        input.close()

        absoluteWay.removeLast()

        return null
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}