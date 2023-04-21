package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result

class Info : Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести информацию о коллекции"
    private val type: ArgsType = ArgsType.NO_ARG
    private var data: Map<String, Any> = mapOf()
    override fun action(data: Map<String, Any>?): Result {
        val s = StringBuilder()
        s.append( "Тип коллекции " + orgs.javaClass.toString() + "\n" )
        s.append( "Дата инициализации " + orgs.getCreationDate() + "\n" )
        s.append( "Количество элементов " + orgs.size + "\n" )
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