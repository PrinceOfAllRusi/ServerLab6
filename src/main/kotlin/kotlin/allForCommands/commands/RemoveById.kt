package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class RemoveById: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент из коллекции по его id"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "min" to "0"
        )
    )

    override fun action(data: Map<String, String?>): Result {

        val id: Int = data["value"]!!.toInt()

        for ( org in orgs ) {
            if ( org.getId() == id ) {
                orgs.remove( org )
                break
            }
        }

        val result = Result()
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}