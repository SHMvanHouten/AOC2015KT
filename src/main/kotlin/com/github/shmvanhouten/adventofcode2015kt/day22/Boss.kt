package com.github.shmvanhouten.adventofcode2015kt.day22

private const val POISON_DAMAGE = 3
private const val POISON_DURATION = 6

data class Boss(
    val damage: Int,
    val hitPoints: Int,
    val poison: Int = 0
) {

    fun receiveDamage(damage: Int): Boss {
        return this.copy(hitPoints = hitPoints - damage)
    }

    fun applyEffects(): Boss {
        return if(this.poison > 0) {
            this.copy(
                hitPoints = hitPoints - POISON_DAMAGE,
                poison = poison - 1
            )
        } else this
    }

    fun receivePoison(): Boss {
        return this.copy(
            poison = poison + POISON_DURATION
        )
    }
}

