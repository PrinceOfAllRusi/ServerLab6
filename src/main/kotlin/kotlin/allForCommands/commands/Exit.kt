package allForCommands.commands

import commands.types.ArgsType
import tools.result.Result

class Exit : Command {

    private val description: String = "завершить программу (без сохранения в файл)"
    private val type: ArgsType = ArgsType.NO_ARG

    override fun action(data: Map<String, Any>?): Result {

        val result = Result(true)

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}