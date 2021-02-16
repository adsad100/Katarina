package com.hubtwork.riot.dto.cdn.championdetail

import com.hubtwork.riot.dto.cdn.Image
import com.hubtwork.riot.dto.cdn.champion.ChampionInfoDTO
import com.hubtwork.riot.dto.cdn.champion.ChampionStatDTO

/**
 *          DTO for Detail Data About Target Champion [ data part ]
 *
 *          * Champion Image src
 *              url :: http://ddragon.leagueoflegends.com/cdn/img/champion/splash/{ Champion ID }_{ Skin Num }.jpg
 *
 *          * Champion Square Image src
 *              url :: http://ddragon.leagueoflegends.com/cdn/{ dd_ver }/img/champion/{ Champion ID }.png
 *
 *          * Champion Loading Image src ( In Game Loading Screen )
 *              url :: http://ddragon.leagueoflegends.com/cdn/img/champion/loading/{ Champion ID }_{ Skin Num }.jpg
 *
 */
data class ChampionDetailInfoDTO(

    var id: String,         // Champion's ID
    var key: String,        // Primary Key

    var name: String,       // Translated Champion Name based on platform ( 플랫폼 기반 번역된 이름 )
    var title: String,      // Champion's Alias ( 챔피언 별명 )

    var image: Image,
    var skinDTOS: ArrayList<ChampionSkinDTO>, // Champion's Skin List

    var lore: String,       // Description about champion's story
    var blurb: String,      // Summary about champion's story

    var allytips: ArrayList<String>,    // Tip String for Player from Riot
    var enemytips: ArrayList<String>,   // Tip String for Enemy Player from Riot

    var tags: ArrayList<String>,        // Position Tags. ( ex_ Fighter, Tank, ... )
    var partype: String,                // Spell Source Type. ( ex_ 마나, 기력 ... )
    var infoDTO: ChampionInfoDTO,             // Champion Assessment ( attack, defence, magic, difficulty )s
    var stats: ChampionStatDTO,            // Champion Base Stat

    var spellDTOS: ArrayList<ChampionSpellDTO>,   // Champion Skill List
    var passiveDTO: ChampionPassiveDTO,           // Passive Skill
    var recommendedDTO: ArrayList<RecommendedDTO> // Recommended Set provided by Riot


)
