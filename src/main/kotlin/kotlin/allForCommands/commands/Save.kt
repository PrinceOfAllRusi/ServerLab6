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

    override fun action(data: Map<String, String?>): Result {

        val wayCollection = data["wayCollection"].toString()
        val collectionXML = data["collectionXML"].toString()
        val wayOrgs = data["wayOrgs"].toString()
        val orgsXML = data["orgsXML"].toString()
        val writer = WriteFile()

        writer.write(wayCollection, collectionXML) //TODO something

        writer.write(wayOrgs, orgsXML)

        val result = Result(false)
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
}