package com.github.shmvanhouten.adventofcode2015kt.day21

import java.lang.Integer.max

data class Character(
    val name: String,
    val equipment: Equipment = Equipment(),
    val damage: Int = equipment.damageModifier(),
    val armor: Int = equipment.armorModifier(),
    var hitPoints: Int = 100


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun receiveDamage(damage: Int): Character {
        return Character(
            this.name,
            this.equipment,
            this.damage,
            this.armor,
            this.hitPoints - (max(1, damage - armor))
        )
    }

    fun equip(equipment: Equipment): Character {
        return Character(
            this.name,
            this.equipment,
            damage = equipment.damageModifier(),
            armor = equipment.armorModifier(),
            hitPoints = this.hitPoints
        )
    }
}

