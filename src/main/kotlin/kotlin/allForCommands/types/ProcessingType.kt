package commands.types

import tools.ConcreteCommand
import tools.input.Input

interface ProcessingType {
    fun processing(input: Input, abstractCommand: ConcreteCommand): Map<String, Any>?
}