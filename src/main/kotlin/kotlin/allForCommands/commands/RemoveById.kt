package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class RemoveById: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент из коллекции по его id"
    private val type: ArgsType = ArgsType.ARG

    override fun action(data: Map<String, Any>?): Result? {
        var strId: String = ""

        if (data != null) {
            strId = data["value"].toString()
        }

        val id: Int = strId.toInt()

        for ( org in orgs ) {
            if ( org.getId() == id ) {
                orgs.remove( org )
                break
            }
        }

        val result = Result(false)
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}