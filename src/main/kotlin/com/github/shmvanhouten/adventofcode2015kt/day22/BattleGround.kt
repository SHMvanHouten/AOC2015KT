package com.github.shmvanhouten.adventofcode2015kt.day22

private const val MAGIC_MISSILE_DAMAGE = 4
private const val DRAIN_DAMAGE = 2

data class BattleGround(
    val player: Player,
    val boss: Boss,
    val turn: Turn = Turn.PLAYER,
    val hardModeDamage:Int = 0
) {

    fun passTurn(effect: Effect? = null): BattleGround {
        return if (turn == Turn.PLAYER && player.hitPoints <= hardModeDamage) {
            this.copy(player = player.receiveHardModeDamage(hardModeDamage))
        } else {

            return if (turn == Turn.PLAYER) {
                passPlayerTurn(effect ?: error("no effect specified for player turn"))
            } else {
                passBossTurn()
            }
        }
    }

    private fun passPlayerTurn(effect: Effect): BattleGround {
        var updatedPlayer = player
            .applyEffects()
            .receiveHardModeDamage(hardModeDamage)
            .drainMana(effect)

        var updatedBoss: Boss = boss.applyEffects()
        when (effect) {
            Effect.POISON -> {
                updatedBoss = updatedBoss.receivePoison()
            }
            Effect.MAGIC_MISSILE -> {
                updatedBoss = updatedBoss.receiveDamage(MAGIC_MISSILE_DAMAGE)
            }
            Effect.DRAIN -> {
                updatedBoss = updatedBoss.receiveDamage(DRAIN_DAMAGE)
                updatedPlayer = updatedPlayer.heal(DRAIN_DAMAGE)
            }
            Effect.RECHARGE -> {
                updatedPlayer = updatedPlayer.recharge()
            }
            Effect.SHIELD -> {
                updatedPlayer = updatedPlayer.shield()
            }
        }
        return this.copy(
            player = updatedPlayer,
            boss = updatedBoss,
            turn = turn.next()
        )
    }

    private fun passBossTurn(): BattleGround {
        return this.copy(
            player = player.applyEffects().receiveDamage(boss.damage),
            boss = boss.applyEffects(),
            turn = turn.next()
        )
    }

    fun playerWon(): Boolean {
        return player.hitPoints > 0 && boss.hitPoints <= 0
    }

    fun bossWon(): Boolean {
        return boss.hitPoints > 0 && player.hitPoints <= 0
    }

    fun effectCanBeCast(effect: Effect): Boolean {
        return player.mana >= effect.manaCost && effectIsOffCooldown(effect)
    }

    private fun effectIsOffCooldown(effect: Effect): Boolean {
        return when (effect) {
            Effect.MAGIC_MISSILE, Effect.DRAIN -> true
            Effect.SHIELD -> player.shield <= 1
            Effect.POISON -> boss.poison <= 1
            Effect.RECHARGE -> player.recharge <= 1
        }
    }

    override fun toString(): String {
        return "BattleGround(player=$player, boss=$boss, turn=$turn)"
    }


}

enum class Turn {
    PLAYER,
    BOSS;

    fun next(): Turn {
        return if(this == PLAYER) {
            BOSS
        } else {
            PLAYER
        }
    }
}