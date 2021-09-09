package com.github.shmvanhouten.adventofcode2015kt.day22

private const val MAGIC_MISSILE_DAMAGE = 4
private const val DRAIN_DAMAGE = 2

data class BattleGround(
    val player: Player,
    val boss: Boss,
    val turn: Turn = Turn.PLAYER
) {
    fun passTurn(effect: Effect? = null): BattleGround {
        val updatedPlayer = player.applyEffects()
        val updatedBoss = boss.applyEffects()

        return if(turn == Turn.PLAYER) {
            passPlayerTurn(updatedPlayer, updatedBoss, effect?: error("no effect specified for player turn"))
        } else {
            passBossTurn(updatedBoss, updatedPlayer)
        }
    }

    private fun passPlayerTurn(player: Player, boss: Boss, effect: Effect): BattleGround {
        var updatedPlayer = player
            .drainMana(effect)

        var updatedBoss: Boss = boss
        when (effect) {
            Effect.POISON -> {
                updatedBoss = boss.receivePoison()
            }
            Effect.MAGIC_MISSILE -> {
                updatedBoss = boss.receiveDamage(MAGIC_MISSILE_DAMAGE)
            }
            Effect.DRAIN -> {
                updatedBoss = boss.receiveDamage(DRAIN_DAMAGE)
                updatedPlayer = updatedPlayer.heal(DRAIN_DAMAGE)
            }
            Effect.RECHARGE -> {
                updatedPlayer = updatedPlayer.recharge()
            }
            Effect.SHIELD -> {
                updatedPlayer = updatedPlayer.shield()
            }
        }
        // todo other effects
        return this.copy(
            player = updatedPlayer,
            boss = updatedBoss,
            turn = turn.next()
        )
    }

    private fun passBossTurn(
        boss: Boss,
        player: Player
    ): BattleGround {
        return this.copy(
            player = player.receiveDamage(boss.damage),
            boss = boss.receiveDamage(0),
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
        return when(effect) {
            Effect.MAGIC_MISSILE, Effect.DRAIN -> true
            Effect.SHIELD -> player.shield <= 1
            Effect.POISON -> boss.poison <= 1
            Effect.RECHARGE -> player.recharge <= 1
        }
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