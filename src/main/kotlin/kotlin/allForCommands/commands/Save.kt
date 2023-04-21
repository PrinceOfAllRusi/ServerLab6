package allForCommands.commands


import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.WriteFile
import tools.result.Result

class Save: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "сохранить коллекцию в файл"
    private val type: ArgsType = ArgsType.FILE

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
    override fun getType(): ArgsType = type
}