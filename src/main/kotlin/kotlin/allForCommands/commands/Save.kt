package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.WriteFile
import tools.result.Result

class Save: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "сохранить коллекцию в файл"
    private var data: Map<String, Any> = mapOf()

    override fun action(data: Map<String, Any>?): Result? {

        val wayCollection: String = data!!["wayCollection"].toString()
        val collectionXML: String = data["collectionXML"].toString()
        val wayOrgs: String = data["wayOrgs"].toString()
        val orgsXML: String = data["orgsXML"].toString()
        val writer = WriteFile()

        writer.write(wayCollection, collectionXML) //TODO something

        writer.write(wayOrgs, orgsXML)

        val result = Result(false)
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
}