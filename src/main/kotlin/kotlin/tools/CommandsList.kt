package tools

import commands.types.ArgsType
import commands.types.ProcessingType
import allForCommands.commands.AbstractCommand

class CommandsList {
    var mapCommands: Map<String, AbstractCommand> = mapOf()
    var mapType: Map<ArgsType, ProcessingType> = mapOf()
    constructor(){}
    constructor(mapCommands: Map<String, AbstractCommand>, mapType: Map<ArgsType, ProcessingType>) {
        this.mapCommands = mapCommands
        this.mapType = mapType
    }
    fun containsCommand(command: String): Boolean = mapCommands.containsKey(command)
    fun getCommand(command: String): AbstractCommand? = mapCommands[command]
    fun getType(type: ArgsType): ProcessingType? = mapType[type]
    fun getDescription(): String = mapCommands.map { "${it.key} : ${it.value.getDescription()}" }.joinToString(separator = "\n")

}