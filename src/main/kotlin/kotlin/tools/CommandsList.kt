package tools

import allForCommands.commands.Command
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import commands.types.ArgsType
import commands.types.ProcessingType

//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
class CommandsList {
    var mapCommands: Map<String, Command> = mapOf()
    var mapType: Map<ArgsType, ProcessingType> = mapOf()
    constructor(){}
    constructor(mapCommands: Map<String, Command>, mapType: Map<ArgsType, ProcessingType>) {
        this.mapCommands = mapCommands
        this.mapType = mapType
    }
    fun containsCommand(command: String): Boolean = mapCommands.containsKey(command)
    fun getCommand(command: String): Command? = mapCommands[command]
    fun getType(type: ArgsType): ProcessingType? = mapType[type]
    fun getDescription(): String = mapCommands.map { "${it.key} : ${it.value.getDescription()}" }.joinToString(separator = "\n")

}