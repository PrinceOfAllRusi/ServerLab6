package transmittedData

import commands.types.ArgsType
import organization.Organization

class CommandsData {
    private var commandsList: Map<String, Map<String, Any>> = mapOf()

    fun getDataForCommands() {
        val fields = mapOf<String, Map<String, Any>>(
            "name" to mapOf<String, Any>(
                "title" to "Введите название вашей организации\n",
                "type" to "string",
                "required" to true
            ),
            "annualTurnover" to mapOf<String, Any>(
                "title" to "Введите название вашей организации\n",
                "type" to "number",
                "required" to true
            ),
        )
        val mapScriptData: Map<String, String> = mapOf("script" to "")
        val mapNoData: Map<String, String> = mapOf()
        val mapOneData: Map<String, String> = mapOf("value" to "")
        val mapTwoData: Map<String, String> = mapOf("value" to "", "organization" to Organization().toString())
        val mapThreeData: Map<String, String> = mapOf("value" to "", "lastOrganization" to Organization().toString(),
            "newOrganization" to Organization().toString())

        commandsList = mapOf("add" to mapTwoData, "clear" to mapNoData, "count_greater_than_annual_turnover" to mapOneData,
            "execute_script" to mapScriptData, "exit" to mapNoData, "filter_starts_with_name" to mapOneData,
            "help" to mapNoData, "info" to mapNoData, "insert_at" to mapOneData,
            "remove_all_by_employees_count" to mapOneData, "remove_at" to mapOneData, "remove_by_id" to mapOneData,
            "remove_lower" to mapOneData, "show" to mapNoData, "update" to mapThreeData)
    }
    fun getCommandsList() = commandsList
    fun setCommandsList(commandsList: Map<String, Map<String, Any>>) {
        this.commandsList = commandsList
    }

}