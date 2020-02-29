package com.github.shmvanhouten.adventofcode2015kt.day21

fun findMostEfficientEquipCost(player: Character, boss: Character): Equipment {
    return permuteEquipments(Shop())
        .filter { playerWinsBattle(player, boss, it) }
        .minBy { it.totalValue() } ?: throw IllegalStateException("no winning equipment found")
}

private fun playerWinsBattle(
    player: Character,
    boss: Character,
    equipment: Equipment
): Boolean {
    val equippedPlayer = player.equip(equipment = equipment)
    return battle(equippedPlayer, boss).winner == player
}
