package com.hubtwork.cdn.model.dto.cdn.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


/**
 *          DTO for item
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemStat (

    var FlatHPPoolMod: Double?,
    var rFlatHPModPerLevel: Double?,
    var FlatMPPoolMod: Double?,
    var rFlatMPModPerLevel: Double?,

    var PercentHPPoolMod: Double?,
    var PercentMPPoolMod: Double?,

    var FlatHPRegenMod: Double?,
    var rFlatHPRegenModPerLevel: Double?,
    var PercentHPRegenMod: Double?,
    var FlatMPRegenMod: Double?,
    var rFlatMPRegenModPerLevel: Double?,
    var PercentMPRegenMod: Double?,

    var FlatArmorMod: Double?,
    var rFlatArmorModPerLevel: Double?,
    var PercentArmorMod: Double?,

    var rFlatArmorPenetrationMod: Double?,
    var rFlatArmorPenetrationModPerLevel: Double?,
    var rPercentArmorPenetrationMod: Double?,
    var rPercentArmorPenetrationModPerLevel: Double?,

    var FlatPhysicalDamageMod: Double?,
    var rFlatPhysicalDamageModPerLevel: Double?,
    var PercentPhysicalDamageMod: Double?,

    var FlatMagicDamageMod: Double?,
    var rFlatMagicDamageModPerLevel: Double?,
    var PercentMagicDamageMod: Double?,

    var FlatMovementSpeedMod: Double?,
    var rFlatMovementSpeedModPerLevel: Double?,
    var PercentMovementSpeedMod: Double?,
    var rPercentMovementSpeedModPerLevel: Double?,

    var FlatAttackSpeedMod: Double?,
    var PercentAttackSpeedMod: Double?,
    var rPercentAttackSpeedModPerLevel: Double?,

    var rFlatDodgeModPerLevel: Double?,
    var PercentDodgeMod: Double?,

    var FlatCritChanceMod: Double?,
    var rFlatCritChanceModPerLevel: Double?,
    var PercentCritDamageMod: Double?,

    var FlatBlockMod: Double?,
    var PercentBlockMod: Double?,
    var FlatSpellBlockMod: Double?,
    var rFlatSpellBlockModPerLevel: Double?,
    var PercentSpellBlockMod: Double?,

    var FlatEXPBonus: Double?,
    var PercentEXPBonus: Double?,

    var rPercentCooldownMod: Double?,
    var rPercentCooldownModPerLevel: Double?,

    var rFlatTimeDeadMod: Double?,
    var rFlatTimeDeadModPerLevel: Double?,
    var rPercentTimeDeadMod: Double?,
    var rPercentTimeDeadModPerLevel: Double?,

    var rFlatGoldPer10Mod: Double?,

    var rFlatMagicPenetrationMod: Double?,
    var rFlatMagicPenetrationModPerLevel: Double?,
    var rPercentMagicPenetrationMod: Double?,
    var rPercentMagicPenetrationModPerLevel: Double?,

    var FlatEnergyRegenMod: Double?,
    var rFlatEnergyRegenModPerLevel: Double?,
    var FlatEnergyPoolMod: Double?,
    var rFlatEnergyModPerLevel: Double?,

    // 생명력 흡수 / 주문흡혈 계수
    var PercentLifeStealMod: Double?,
    var PercentSpellVampMod: Double?
)