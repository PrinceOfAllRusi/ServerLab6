package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result

class Clear: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "очистить коллекцию"
    private var data: Map<String, Any> = mapOf()
    private var fields: Map<String, Map<String, String>> = mapOf()

    override fun action(data: Map<String, Any>?): Result {
        orgs.clear()
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