package commands


import com.thoughtworks.xstream.XStream
import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.WriteFile
import tools.result.Result

/**
 * Save
 *
 * @constructor Create empty Save
 */
class Save: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "сохранить коллекцию в файл"
    private val type: ArgsType = ArgsType.FILE

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {

        val wayCollection: String = data!!["wayCollection"].toString()
        val collectionXML: String = data["collectionXML"].toString()
        val wayOrgs: String = data["wayOrgs"].toString()
        val orgsXML: String = data["orgsXML"].toString()
        val writer = WriteFile()

        writer.write(wayCollection, collectionXML) //TODO something

        writer.write(wayOrgs, orgsXML)

        return null
    }

    /**
     * Get description
     *
     * @return
     */
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}