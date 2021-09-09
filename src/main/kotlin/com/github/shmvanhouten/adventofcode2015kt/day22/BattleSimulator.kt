package com.github.shmvanhouten.adventofcode2015kt.day22

fun simulateBattleWithEffects(battleGround: BattleGround, effects: List<Effect>): BattleGround {
    var updateBattle = battleGround
    val effectsIterator = effects.iterator()
    while (!updateBattle.playerWon() && !updateBattle.bossWon()) {
        println(updateBattle)
        if(updateBattle.turn == Turn.PLAYER) {
            val effect = effectsIterator.next()
            println("casting $effect!")
            updateBattle = updateBattle.passTurn(effect)
        } else {
            val armor = if(updateBattle.player.shield > 0) 7
            else 0
            println("boss hits for ${updateBattle.boss.damage - armor}!")
            updateBattle = updateBattle.passTurn()
        }
    }
    println(updateBattle)
    if(updateBattle.playerWon()) {
        println("Player won!")
        println("used ${effects.sumBy { it.manaCost }} mana")
    } else {
        println("Boss won!")
    }
    return updateBattle
}