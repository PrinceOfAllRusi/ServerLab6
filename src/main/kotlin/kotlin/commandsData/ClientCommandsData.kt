package CommandsData

class ClientCommandsData {
    private var name: String
    private var mapData: Map<String, String>
    constructor(){
        name = ""
        mapData = mapOf()
    }
    constructor (name: String, mapCommands: Map<String, String>) {
        this.name = name
        this.mapData = mapCommands
    }
    fun getName() = name
    fun setName(name: String) {
        this.name = name
    }
    fun getMapData() = mapData
    fun setMapData(mapCommands: Map<String, String>) {
        this.mapData = mapCommands
    }
}