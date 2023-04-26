package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.result.Result

class Add : AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "добавить новый элемент в коллекцию"
    private val type: ArgsType = ArgsType.OBJECT
    private var data: Map<String, Any> = mapOf("organization" to Organization())
    private var fields: Map<String, Map<String, String>> = mapOf(
        "name" to mapOf<String, String>(
            "title" to "Введите название вашей организации\n",
            "type" to "String"
        ),
        "annualTurnover" to  mapOf<String, String>(
            "title" to "Введите название вашей организации\n",
            "type" to "Double",
            "min" to "1"
        ),
        "employeesCount" to mapOf<String, String>(
            "title" to "Введите количество сотрудников в вашей организации\n",
            "type" to "Int",
            "min" to "1"
        ),
        "X" to mapOf<String, String>(
            "title" to "Введите координаты вашей организации по оси X\n",
            "type" to "Int",
            "min" to "-312"
        ),
        "Y" to mapOf<String, String>(
            "title" to "Введите координаты вашей организации по оси Y\n",
            "type" to "Long",
            "max" to "212"
        ),
        "Type" to mapOf<String, String>(
            "title" to "Выбирете тип вашей организации из данных вариантов\n",
            "type" to "OrganizationType"
        ),
        "Street" to mapOf<String, String>(
            "title" to "Введите название улицы, на которой расположена ваша организация\n",
            "type" to "String"
        ),
        "ZipCode" to mapOf<String, String>(
            "title" to "Введите код улицы, на которой расположена ваша организация\n",
            "type" to "String",
            "length" to "27"
        )
    )

    override fun action(data: Map<String, Any>?): Result {
        val orgComp = OrganizationComparator()
        var org: Organization? = null
        if ( data != null ) {
            org = data.get("organization") as Organization?
        }

        if ( org != null ){
            orgs.add(org)
            orgs.sortWith(orgComp)
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