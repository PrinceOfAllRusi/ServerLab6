package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.Organization
import tools.ConcreteCommand
import tools.CreateOrganization
import tools.input.Input

class ProcessingTypeObject : ProcessingType, KoinComponent {

    private val creator: CreateOrganization by inject()
    override fun processing(input: Input, abstractCommand: ConcreteCommand): Map<String, Any>? {

//        val org = creator.create(input, null)
//        if ( org == null ) {
//            return null
//        }

        val org = abstractCommand.getOrganization()

        val map: Map<String, Any> = mapOf("organization" to org)

        return map
    }
}