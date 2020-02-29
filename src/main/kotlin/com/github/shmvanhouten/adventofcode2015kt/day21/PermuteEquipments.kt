package com.github.shmvanhouten.adventofcode2015kt.day21

fun permuteEquipments(shop: Shop): List<Equipment> {
    return shop.weapons
        .flatMap { weapon ->
            addEmptySlot(shop.armors)
                .map { armor -> Equipment(weapon, armor) }
        }
        .flatMap { equipment ->
            addEmptySlot(shop.rings)
                .map { ring -> equipment.copy(ring1 = ring) }
        }
        .flatMap { equipment ->
            addEmptySlot(shop.rings.minus(equipment.ring1))
                .map { ring -> equipment.copy(ring2 = ring) }
        }
}

private fun <T> addEmptySlot(items: List<T?>): List<T?> {
    val possibleEquips = items.toMutableList()
    possibleEquips.add(null)
    return possibleEquips
}