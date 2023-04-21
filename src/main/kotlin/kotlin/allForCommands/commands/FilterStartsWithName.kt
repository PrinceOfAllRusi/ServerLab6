package allForCommands.commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import java.lang.StringBuilder
import tools.result.Result


class FilterStartsWithName: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести элементы, значение поля name которых начинается с заданной подстроки"
    private val type: ArgsType = ArgsType.ARG
    override fun action(data: Map<String, Any>?): Result {
        val str: String = data?.get("value").toString()
        val s: StringBuilder = StringBuilder()
        for (org in orgs) {

            if ( str.length > org.getName()!!.length) {
                continue
            }

            val name = str.toCharArray()
            val orgName = org.getName()!!.toCharArray()
            var condition = true

            for (i in 0..name.size-1) {
                if ( name[i] != orgName[i] ) {
                    condition = false
                    break
                }
            }

            if ( condition ) {
                s.append( org.toString() + "\n" )
            }
        }

        val result: Result = Result(false)
        result.setMessage(s.toString())

        return result
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}