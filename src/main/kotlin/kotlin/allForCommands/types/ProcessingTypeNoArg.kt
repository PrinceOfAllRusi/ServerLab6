package commands.types

import tools.ConcreteCommand
import tools.input.Input

class ProcessingTypeNoArg : ProcessingType {
    override fun processing(input: Input, abstractCommand: ConcreteCommand): Map<String, Any>? {
        return null
    }
}