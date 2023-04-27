package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.CreateOrganization
import tools.result.Result

class Update: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "обновить значение элемента коллекции, id которого равен заданному"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int"
        ),
        "name" to mapOf<String, String>(
            "title" to "Введите название вашей организации\n",
            "type" to "String",
            "null" to "true"
        ),
        "annualTurnover" to  mapOf<String, String>(
            "title" to "Введите название вашей организации\n",
            "type" to "Double",
            "min" to "1",
            "null" to "true"
        ),
        "employeesCount" to mapOf<String, String>(
            "title" to "Введите количество сотрудников в вашей организации\n",
            "type" to "Int",
            "min" to "1",
            "null" to "true"
        ),
        "X" to mapOf<String, String>(
            "title" to "Введите координаты вашей организации по оси X\n",
            "type" to "Int",
            "min" to "-312",
            "null" to "true"
        ),
        "Y" to mapOf<String, String>(
            "title" to "Введите координаты вашей организации по оси Y\n",
            "type" to "Long",
            "max" to "212",
            "null" to "true"
        ),
        "Type" to mapOf<String, String>(
            "title" to "Выбирете тип вашей организации из данных вариантов\n",
            "type" to "OrganizationType",
            "null" to "true"
        ),
        "Street" to mapOf<String, String>(
            "title" to "Введите название улицы, на которой расположена ваша организация\n",
            "type" to "String",
            "null" to "true"
        ),
        "ZipCode" to mapOf<String, String>(
            "title" to "Введите код улицы, на которой расположена ваша организация\n",
            "type" to "String",
            "length" to "27",
            "null" to "true"
        )
    )

    override fun action(data: Map<String, String?>): Result {

        val id = data["value"]!!.toInt()
        var lastOrganization = Organization()
        for ( org in orgs ) {
            if ( id == org.getId() ) {
                lastOrganization = org
                break
            }
        }
        val newOrganization: Organization = CreateOrganization().create(data, lastOrganization)
        val orgComp = OrganizationComparator()

        orgs.remove( lastOrganization )
        orgs.add( newOrganization )

        orgs.sortWith( orgComp )

        val result = Result(false)
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}