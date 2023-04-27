package allForCommands.commands

import tools.result.Result

class Exit : AbstractCommand() {

    private val description: String = "завершить программу (без сохранения в файл)"
    private var data: Map<String, Any> = mapOf()
    private var fields: Map<String, Map<String, String>> = mapOf()

    override fun action(data: Map<String, Any>?): Result {

        val result = Result(true)

        return result
    }
    override fun getDescription(): String = description
    fun getData() = data
    fun setData(data: Map<String, Any>) {
        this.data = data
    }
    override fun getFields() = fields
}