package CommandsData

class ReceiveCommandsData {
    private var name: String
    private var mapCommands: Map<String, String>
    constructor(){
        name = ""
        mapCommands = mapOf()
    }
    constructor (name: String, mapCommands: Map<String, String>) {
        this.name = name
        this.mapCommands = mapCommands
    }
    fun getName() = name
    fun setName(name: String) {
        this.name = name
    }
    fun getMapCommands() = mapCommands
    fun setMapCommands(mapCommands: Map<String, String>) {
        this.mapCommands = mapCommands
    }
}