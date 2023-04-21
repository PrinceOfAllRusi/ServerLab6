package allForCommands.commands

import commands.types.ArgsType
import tools.result.Result

interface Command {
    fun action(map: Map<String, Any>?): Result?
    fun getDescription(): String
    fun getType(): ArgsType
}