package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.Organization
import tools.ConcreteCommand
import tools.CreateOrganization
import tools.input.Input

class ProcessingTypeIndexWithObject : ProcessingType, KoinComponent {

    private val creator: CreateOrganization by inject()
    override fun processing(input: Input, abstractCommand: ConcreteCommand): Map<String, Any> {
        val value = abstractCommand.getIndex()

        val newOrganization = abstractCommand.getOrganization()

        val map: Map<String, Any> = mapOf("value" to value, "organization" to newOrganization)

        return map
    }
}