package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.*
import tools.input.InputFile
import tools.result.Result
import kotlin.collections.ArrayList

class ExecuteScript: Command, KoinComponent {

    private val absoluteWay: ArrayList<String> by inject()
    private val description: String = "считать и исполнить скрипт из указанного файла"
    private val type: ArgsType = ArgsType.SCRIPT
    private var data: Map<String, Any> = mapOf("script" to "")
    override fun action(data: Map<String, Any>?): Result? {

        if ( data == null ) {
            return null
        }

        val s = data.get("script").toString()

        val input = InputFile(s)

        val commandProcessor = CommandProcessor()
            commandProcessor.process(input)

        input.close()

        absoluteWay.removeLast()

        return null
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
}