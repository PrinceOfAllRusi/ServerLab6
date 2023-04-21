package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.ConcreteCommand
import tools.input.Input

class ProcessingTypeIdWithObject : ProcessingType, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    override fun processing(input: Input, abstractCommand: ConcreteCommand): Map<String, Any> {
        val newOrganization = abstractCommand.getOrganization()
        val id = newOrganization.getId()!!
        var lastOrganization = Organization()

        for ( org in orgs ) {
            if ( id == org.getId() ) {
                lastOrganization = org
            }
        }

        val map: Map<String, Any> = mapOf("value" to id, "lastOrganization" to lastOrganization, "newOrganization" to newOrganization)

        return map

    }
}