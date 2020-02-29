package com.github.shmvanhouten.adventofcode2015kt.day21

data class Equipment(
    val weapon: Weapon? = null,
    val armor: Armor? = null,
    val ring1: Ring? = null,
    val ring2: Ring? = null
) {
    fun damageModifier(): Int {
        return (weapon?.damage ?: 0) +
                (ring1?.damage ?: 0) +
                (ring2?.damage ?: 0)
    }

    fun armorModifier(): Int {
        return (armor?.armor ?: 0) +
                (ring1?.armor ?: 0) +
                (ring2?.armor ?: 0)
    }

    fun totalValue(): Int {
        return (weapon?.cost ?: 0) +
        (armor?.cost ?: 0) +
        (ring1?.cost ?: 0) +
        (ring2?.cost ?: 0)
    }
}

data class Weapon(val damage: Int, val cost: Int = 0)

data class Armor(val armor: Int, val cost: Int = 0)

data class Ring(val armor: Int, val damage: Int, val cost: Int = 0)