package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.CommandsList
import tools.result.Result


class Help : AbstractCommand(), KoinComponent {

    private val description: String = "вывести справку по доступным командам"
    private val commandsList: CommandsList by inject()
    private val type: ArgsType = ArgsType.NO_ARG
    private var data: Map<String, Any> = mapOf()
    private var fields: Map<String, Map<String, String>> = mapOf()
    override fun action(data: Map<String, Any>?): Result {

        val s = commandsList.getDescription() + "\n"
        val result = Result(false)
        result.setMessage(s.toString())

        return result
    }
    override fun getDescription(): String = description
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
    override fun getFields() = fields
    override fun getType(): ArgsType = type
}