package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class CountGreaterThanAnnualTurnover: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести количество элементов, значение поля annualTurnover которых больше заданного"
    private val type: ArgsType = ArgsType.ARG
    private val value: Int = 0
    private var data: Map<String, Any> = mapOf("value" to value)
    override fun action(data: Map<String, Any>?): Result? {
        if ( data == null ) {
            return null
        }
        val strTurnover =  data.get("value").toString()
        val turnover: Double = strTurnover.toDouble()
        var count = 0
        for (org in orgs) {
            if (org.getAnnualTurnover()!! > turnover) {
                count++
            }
        }

        val result = Result(false)
        result.setMessage(count.toString() + "\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
}