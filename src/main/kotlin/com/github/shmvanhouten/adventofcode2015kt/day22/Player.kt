package com.github.shmvanhouten.adventofcode2015kt.day22

private const val RECHARGE_DURATION = 5
private const val RECHARGE_AMOUNT = 101

private const val SHIELD_DURATION = 6
private const val SHIELD_AMOUNT = 7

data class Player(
    val hitPoints: Int = 50,
    val mana: Int = 500,
    val armor: Int = 0,
    val recharge: Int = 0,
    val shield: Int = 0
) {

    fun receiveDamage(damage: Int): Player {
        var updatedDamage: Int = damage - armor

        if(updatedDamage <= 0) updatedDamage = 1

        return this.copy(
            hitPoints = hitPoints - updatedDamage
        )
    }

    fun receiveHardModeDamage(hardModeDamage: Int): Player {
        return this.copy(
            hitPoints = hitPoints - hardModeDamage
        )
    }

    fun applyEffects(): Player {
        val updatedMana = if(recharge > 0) {
            mana + RECHARGE_AMOUNT
        } else {
            mana
        }
        val updatedRecharge = if(recharge > 0) {
            recharge - 1
        } else recharge
        var updatedArmor = 0
        val updatedShield = if(shield > 0) {
            updatedArmor = SHIELD_AMOUNT
            shield - 1
        } else shield
        return this.copy(
            mana = updatedMana,
            recharge = updatedRecharge,
            armor = updatedArmor,
            shield = updatedShield
        )
    }

    fun drainMana(effect: Effect): Player {
        return this.copy(
            mana = mana - effect.manaCost
        )
    }

    fun heal(drainDamage: Int): Player {
        return this.copy(
            hitPoints = hitPoints + drainDamage
        )
    }

    fun recharge(): Player {
        return this.copy(
            recharge = recharge + RECHARGE_DURATION
        )
    }

    fun shield(): Player {
        return this.copy(
            shield = shield + SHIELD_DURATION
        )
    }
}