package allForCommands.commands

import commands.types.ArgsType
import tools.result.Result

abstract class AbstractCommand {
    private var description = ""
    private var fields: Map<String, Map<String, String>> = mapOf()
    private var type = ArgsType.NO_ARG

    open fun action(map: Map<String, Any>?): Result? {
        return null
    }
    open fun getDescription(): String = description
    open fun getFields() = fields
    open fun getType(): ArgsType = type
}