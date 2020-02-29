package com.github.shmvanhouten.adventofcode2015kt.day21

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day21Test {

    @Nested
    inner class Part1 {

        @Test
        internal fun `player vs boss, round 1`() {
            val player = Character(name = "player", damage = 2, armor = 2, hitPoints = 5)
            val boss = Character(name = "boss", damage = 4, armor = 0, hitPoints = 4)

            assertThat(battle(player, boss).winner, equalTo(player))
        }

        @Test
        internal fun `player vs boss, example`() {
            //suppose you have 8 hit points, 5 damage, and 5 armor,
            // and that the boss has 12 hit points, 7 damage, and 2 armor
            val player = Character(name = "player", damage = 5, armor = 5, hitPoints = 8)
            val boss = Character(name = "boss", damage = 7, armor = 2, hitPoints = 12)

            val battle = battle(player, boss)
            assertThat(battle.winner, equalTo(player))
        }

        @Test
        internal fun part1() {
            val player = Character("player", hitPoints = 100)
            val boss = Character("boss", damage = 8, armor = 2, hitPoints = 100)

            val equipment = findMostEfficientEquipCost(player, boss)
            assertThat(equipment.totalValue(), equalTo(91))
        }
    }

}
