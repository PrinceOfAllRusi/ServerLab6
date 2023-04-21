package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class InsertAt: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "добавить новый элемент в заданную позицию"
    private val type: ArgsType = ArgsType.INDEX_WITH_OBJECT
    private val value: Int = 0
    private var data: Map<String, Any> = mapOf("value" to value, "organization" to Organization())

    override fun action(data: Map<String, Any>?): Result? {

        if ( data == null ) {
            return null
        }

        val strIndex: String = data["value"].toString()
        val index = strIndex.toInt()
        val org = data.get("organization") as Organization

        orgs.add(index, org)

        val result = Result(false)
        result.setMessage("Done\n")
        fun getData() = data
        fun setData(data: Map<String, Any>) {
            this.data = data
        }

        return result

    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
}