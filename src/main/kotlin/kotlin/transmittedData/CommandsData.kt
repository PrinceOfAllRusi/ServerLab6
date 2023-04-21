package transmittedData

import commands.types.ArgsType
import organization.Organization

class CommandsData {
    private var commandsList: Map<String, Map<String, Any>>
    private var typesList: Map<ArgsType, Int>

    constructor() {
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

        typesList = mapOf(ArgsType.NO_ARG to 0, ArgsType.ARG to 1, ArgsType.ARG_WITH_OBJECT to 3,
            ArgsType.INDEX_WITH_OBJECT to 2, ArgsType.SCRIPT to 1, ArgsType.OBJECT to 2)
    }

    fun getCommandsList() = commandsList
    fun setCommandsList(commandsList: Map<String, Map<String, Any>>) {
        this.commandsList = commandsList
    }
    fun getTypeList() = typesList
    fun setTypeList(typesList: Map<ArgsType, Int>) {
        this.typesList = typesList
    }

}