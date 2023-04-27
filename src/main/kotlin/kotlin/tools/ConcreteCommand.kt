package tools

import allForCommands.commands.AbstractCommand
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.result.Result

class ConcreteCommand: AbstractCommand(), KoinComponent {
    private var description: String = ""
    private var data: Map<String, Any> = mapOf("organization" to Organization())
    private var fields: Map<String, Map<String, String>> = mapOf()

    override fun getDescription(): String = description
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
    override fun getFields() = fields
}