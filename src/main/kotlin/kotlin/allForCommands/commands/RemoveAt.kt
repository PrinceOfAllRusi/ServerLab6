package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result

class RemoveAt: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент, находящийся в заданной позиции коллекции"
    private val value: Int = 0
    private var data: Map<String, Any> = mapOf("value" to value)
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "min" to "1"
        )
    )

    override fun action(data: Map<String, Any>?): Result? {
        val indexOrg: String = data!!["value"].toString()
        val index = indexOrg.toInt()
        orgs.removeAt(index)

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
}
