package com.github.shmvanhouten.adventofcode2015kt.day21

fun battle(character1: Character, character2: Character): BattleResult {
    var player = character1
    var boss = character2
    while (true) {
        boss = boss.receiveDamage(player.damage)
        if (boss.hitPoints <= 0) {
            return BattleResult(winner = player, loser = boss)
        }

        player = player.receiveDamage(boss.damage)
        if (player.hitPoints <= 0) {
            return BattleResult(winner = boss, loser = player)
        }
    }
}

data class BattleResult(val winner: Character, val loser: Character)


