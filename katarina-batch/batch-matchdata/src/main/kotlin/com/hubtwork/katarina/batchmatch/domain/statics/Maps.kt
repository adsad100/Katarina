package com.hubtwork.katarinaapi.dto.riotapi.statics

enum class Maps(val mapId: Int, val mapName: String) {
    SummonersRiftSummer(1, "Summoner's Rift Summer"),
    SummonersRiftAutumn(2, "Summoner's Rift Autumn"),
    Tutorial(3, "Tutorial"),
    TwistedTreelineOrigin(4, "Twisted Treeline"),
    Dominion(8, "Dominion"),
    TwistedTreelineLast(10, "Twisted Treeline"),
    SummonersRift(11, "Summoner's Rift"),
    HowlingAbyss(12, "Howling Abyss"),
    ButcherBridge(14, "Butcher's Bridge"),
    CosmicRuins(16, "Dark Star"),
    ValoranCityPark(18, "Star Guardian Invasion"),
    Substructure(19, "PROJECT: Hunters"),
    CrashSite(20, "Odyssey"),
    NexusBlitz(21, "NexusBlitz");

    companion object {

        fun valueOf(mapId: Int): Maps? = Maps.values().find { it.mapId == mapId }

        fun getName(id: Int): String {
            for (map in Maps.values()) {
                if (map.mapId == id) {
                    return map.mapName
                }
            }
            return "[ Err ] No Such queue mapped info"
        }

    }
}
