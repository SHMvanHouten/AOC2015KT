package com.github.shmvanhouten.adventofcode2015kt.day22

import com.github.shmvanhouten.adventofcode2020.util.queueOf
import java.util.*

fun findMostManaEfficientBattle(player: Player, boss: Boss, hardModeDamage: Int = 0): Int? {

    return permuteBattlesWherePlayerWon(BattleGround(player, boss, hardModeDamage = hardModeDamage))
        .map { it.second }
        .map { it.sumBy { effect -> effect.manaCost } }
        .maxOrNull()
}

private fun permuteBattlesWherePlayerWon(startingBattleGround: BattleGround): List<Pair<BattleGround, List<Effect>>> {
    val unfinishedBattles: Queue<Pair<BattleGround, List<Effect>>> = queueOf(startingBattleGround to emptyList())
    val finishedBattles: MutableList<Pair<BattleGround, List<Effect>>> = mutableListOf()
    var maxManaUsed = Int.MAX_VALUE

    while (unfinishedBattles.isNotEmpty()) {
        val (battleGround, effectsUsed) = unfinishedBattles.poll()
        val manaUsed = effectsUsed.sumBy { it.manaCost }
        when {
            manaUsed > maxManaUsed -> {
                // drop battle
            }
            battleGround.bossWon() -> {
                // drop battle
            }
            battleGround.playerWon() && manaUsed <= maxManaUsed -> {

                finishedBattles.add(battleGround to effectsUsed)
                maxManaUsed = manaUsed

            }
            battleGround.turn == Turn.BOSS -> {
                unfinishedBattles.offer(battleGround.passTurn() to effectsUsed)
            }
            battleGround.turn == Turn.PLAYER -> {
                Effect.values()
                    .filter { battleGround.effectCanBeCast(it) }
                    .map {
                        battleGround.passTurn(it) to it
                    }.forEach {
                        unfinishedBattles.offer(it.first to effectsUsed + it.second)
                    }
            }
        }
    }
    return finishedBattles.toList()
}
