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

fun permuteEquipments(shop: Shop): List<Equipment> {
    return shop.weapons
        .flatMap { weapon ->
            addEmptySlot(shop.armors).map { armor -> Equipment(weapon, armor) }
        }
        .flatMap { equipment ->
            addEmptySlot(shop.rings).map { ring -> equipment.copy(ring1 = ring) }
        }
        .flatMap { equipment ->
            addEmptySlot(shop.rings.minus(equipment.ring1)).map { ring -> equipment.copy(ring2 = ring) }
        }
}

private fun <T> addEmptySlot(rings: List<T?>): List<T?> {
    val possibleEquips = rings.toMutableList()
    possibleEquips.add(null)
    return possibleEquips
}