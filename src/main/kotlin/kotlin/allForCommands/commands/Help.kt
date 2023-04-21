package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.CommandsList
import tools.result.Result


class Help : Command, KoinComponent {

    private val description: String = "вывести справку по доступным командам"
    private val commandsList: CommandsList by inject()
    private var type: ArgsType = ArgsType.NO_ARG
    override fun action(data: Map<String, Any>?): Result? {

        val s = commandsList.getDescription() + "\n"
        val result = Result(false)
        result.setMessage(s.toString())

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}