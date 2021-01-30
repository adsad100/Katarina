package com.hubtwork.cdn.constant

enum class Platform(val id: String, val localeName: String) {
    BR("BR1", "br"),
    EUNE("EUN1", "eune"),
    EUW("EUW1", "euw"),
    JP("JP1", "jp"),
    KR("KR", "kr"),
    LAN("LA1", "lan"),
    LAS("LA2", "las"),
    NA("NA1", "na"),
    OCE("OC1", "oce"),
    RU("RU", "ru"),
    TR("TR1", "tr");

    fun getUrlApi() = "https://ddragon.leagueoflegends.com/api/"

    fun getUrlCdn() = "https://ddragon.leagueoflegends.com/cdn/"

    fun getUrlCdn(version: String, locale: Locale) = "https://ddragon.leagueoflegends.com/cdn/$version/data/${locale.id}/"

    fun getUrlRealms() = "https://ddragon.leagueoflegends.com/realms/${this.localeName}.json"

    companion object {

        fun getPlatformById(id: String): Platform {
            for (platform in Platform.values()) {
                if (platform.id.equals(id, ignoreCase = true)) {
                    return platform
                }
            }
            return KR
        }

        fun getPlatformByName(name: String): Platform {
            for (platform in Platform.values()) {
                if (platform.name.equals(name, ignoreCase = true)) {
                    return platform
                }
            }
            return KR
        }
    }
}
