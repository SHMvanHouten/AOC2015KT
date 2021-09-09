package com.github.shmvanhouten.adventofcode2015kt.day22

fun findMostManaEfficientBattle(player: Player, boss: Boss): Int? {

    return BattleGround(player, boss)
        .permuteBattlesWherePlayerWon()
        .map { it.second }
        .map { it.sumBy { effect -> effect.manaCost } }
        .maxOrNull()
}

private tailrec fun BattleGround.permuteBattlesWherePlayerWon(effectsUsed: List<Effect> = emptyList()): List<Pair<BattleGround, List<Effect>>> {
    if (this.playerWon()) {
        return listOf(this to effectsUsed)
    } else if (this.bossWon()) {
        return emptyList()
    }
    if (this.turn != Turn.BOSS) {
        return Effect.values()
            .filter { this.effectCanBeCast(it) }
            .map {
                this.passTurn(it).permuteBattlesWherePlayerWon()
            }.flatten()
    }
    return this.passTurn().permuteBattlesWherePlayerWon()
}
