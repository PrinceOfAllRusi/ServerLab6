package commands.types

import tools.ConcreteCommand
import tools.input.Input

class ProcessingTypeArg: ProcessingType {
    override fun processing(input: Input, abstractCommand: ConcreteCommand): Map<String, Any> {
        val org = abstractCommand.getOrganization()
        val id = org.getId()
        val index = abstractCommand.getIndex()
        var value: Int = 0
        if ( index >= 0 ) {
            value = index
        } else {
            if (id != null) {
                value = id
            }
        }

        val map: Map<String, Any> = mapOf("value" to value)
        return map
    }
}