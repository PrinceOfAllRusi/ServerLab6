package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class RemoveById: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент из коллекции по его id"
    private val type: ArgsType = ArgsType.ARG
    private val value: Int = 0
    private var data: Map<String, Any> = mapOf("value" to value)
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "min" to "0"
        )
    )

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
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
    override fun getFields() = fields
    override fun getType(): ArgsType = type
}