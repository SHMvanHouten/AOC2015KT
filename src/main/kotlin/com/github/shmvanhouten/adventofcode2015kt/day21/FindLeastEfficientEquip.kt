package com.github.shmvanhouten.adventofcode2015kt.day21

fun findLeastEfficientEquipCost(player: Character, boss: Character): Equipment {
    return permuteEquipments(Shop())
        .filter { playerLosesBattle(player, boss, it) }
        .maxBy { it.totalValue() } ?: throw IllegalStateException("no losing equipment found")
}

private fun playerLosesBattle(
    player: Character,
    boss: Character,
    equipment: Equipment
): Boolean {
    val equippedPlayer = player.equip(equipment = equipment)
    return battle(equippedPlayer, boss).winner == boss
}