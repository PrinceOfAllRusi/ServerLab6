package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.result.Result

class Update: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "обновить значение элемента коллекции, id которого равен заданному"
    private val type: ArgsType = ArgsType.ARG_WITH_OBJECT
    private var data: Map<String, Any> = mapOf("value" to 0, "lastOrganization" to Organization(), "newOrganization" to Organization())

    override fun action(data: Map<String, Any>?): Result? {
        val orgComp = OrganizationComparator()
        val map: Map<String, Any>? = data

        if ( map == null ) {
            return null
        }

        val lastOrganization: Organization = map.get("lastOrganization") as Organization
        val newOrganization: Organization = map.get("newOrganization") as Organization

        orgs.remove( lastOrganization )
        orgs.add( newOrganization )

        orgs.sortWith( orgComp )

        val result = Result(false)
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
}