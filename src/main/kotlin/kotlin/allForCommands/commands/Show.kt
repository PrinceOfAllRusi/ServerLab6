package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class Show: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести все элементы коллекции"
    private val type: ArgsType = ArgsType.NO_ARG
    private var data: Map<String, Any> = mapOf()

    override fun action(data: Map<String, Any>?): Result {
        val s = StringBuilder()
        for (org in orgs) {
            s.append(org.toString() + "\n")
        }
        val result = Result(false)
        result.setMessage(s.toString())

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
}