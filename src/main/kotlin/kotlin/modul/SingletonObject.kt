package modul

import allForCommands.commands.*
import commands.types.*
import org.koin.dsl.module
import organization.MyCollection
import organization.Organization
import tools.CommandsList
import tools.CreateOrganization
import allForCommands.commands.AbstractCommand
import kotlin.collections.ArrayList

object SingletonObject {

    val mod = module {
        single<MyCollection<Organization>> { MyCollection() }
        single<ArrayList<String>> { ArrayList() }
        single<CreateOrganization> { CreateOrganization() }

        val help: Help = Help()
        val info: Info = Info()
        val show: Show = Show()
        val add: Add = Add()
        val update: Update = Update()
        val remove_by_id: RemoveById = RemoveById()
        val clear: Clear = Clear()
        val execute_script: ExecuteScript = ExecuteScript()
        val exit: Exit = Exit()
        val insert_at: InsertAt = InsertAt()
        val remove_at: RemoveAt = RemoveAt()
        val remove_lower: RemoveLower = RemoveLower()
        val remove_all_by_employees_count: RemoveAllByEmployeesCount = RemoveAllByEmployeesCount()
        val count_greater_than_annual_turnover: CountGreaterThanAnnualTurnover = CountGreaterThanAnnualTurnover()
        val filter_starts_with_name: FilterStartsWithName = FilterStartsWithName()

        val mapCommand: Map<String, AbstractCommand> = mapOf("help" to help, "info" to info, "show" to show, "add" to add,
            "update" to update, "remove_by_id" to remove_by_id, "clear" to clear, "execute_script" to execute_script,
            "exit" to exit, "insert_at" to insert_at, "remove_at" to remove_at, "remove_lower" to remove_lower,
            "remove_all_by_employees_count" to remove_all_by_employees_count,
            "count_greater_than_annual_turnover" to count_greater_than_annual_turnover,
            "filter_starts_with_name" to filter_starts_with_name)

        val mapCommand2: Map<String, Map<String, Map<String, String>>> = mapOf("help" to Help().getFields(),
            "info" to Info().getFields(), "show" to Show().getFields(), "add" to Add().getFields(),
            "update" to Update().getFields(), "remove_by_id" to RemoveById().getFields(),
            "clear" to Clear().getFields(), "execute_script" to ExecuteScript().getFields(),
            "exit" to Exit().getFields(), "insert_at" to InsertAt().getFields(),
            "remove_at" to RemoveAt().getFields(), "remove_lower" to RemoveLower().getFields(),
            "remove_all_by_employees_count" to RemoveAllByEmployeesCount().getFields(),
            "count_greater_than_annual_turnover" to CountGreaterThanAnnualTurnover().getFields(),
            "filter_starts_with_name" to FilterStartsWithName().getFields())

        single<Map<String, Map<String, Map<String, String>>>> { mapCommand2 }

        val processingTypeObject = ProcessingTypeObject()
        val processingTypeArg = ProcessingTypeArg()
        val processingTypeNoArg = ProcessingTypeNoArg()
        val processingTypeArgWhiteObject = ProcessingTypeObject()
        val processingTypeIndexWithObject = ProcessingTypeIndexWithObject()
        val processingTypeString = ProcessingTypeScript()
        val processingTypeWriteFile = ProcessingTypeWriteFile()

        val mapType: Map<ArgsType, ProcessingType> = mapOf( ArgsType.OBJECT to processingTypeObject,
            ArgsType.ARG to processingTypeArg, ArgsType.NO_ARG to processingTypeNoArg,
            ArgsType.ARG_WITH_OBJECT to processingTypeArgWhiteObject,
            ArgsType.INDEX_WITH_OBJECT to processingTypeIndexWithObject,
            ArgsType.SCRIPT to processingTypeString, ArgsType.FILE to processingTypeWriteFile)

        val CommandsList: CommandsList = CommandsList( mapCommand, mapType )

        single<CommandsList> { CommandsList }
    }
}