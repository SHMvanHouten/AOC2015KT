package com.github.shmvanhouten.adventofcode2015kt.day21

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class BattleTest {
    @Test
    internal fun `1 damage does 1 hp of damage to 0 armor`() {
        val player = Character("player", damage = 1)
        val boss = Character("boss", armor = 0, hitPoints = 5)

        val resultingBoss = boss.receiveDamage(player.damage)

        assertThat(resultingBoss.hitPoints, equalTo(4))
    }

    @Test
    internal fun `2 damage does 2 hp of damage to 0 armor`() {
        val player = Character("player", damage = 2)
        val boss = Character("boss", armor = 0, hitPoints = 5)

        val resultingBoss = boss.receiveDamage(player.damage)

        assertThat(resultingBoss.hitPoints, equalTo(3))
    }

    @Test
    internal fun `2 damage does 1 hp of damage to 1 armor`() {
        val player = Character("player", damage = 2)
        val boss = Character("boss", armor = 1, hitPoints = 5)

        val resultingBoss = boss.receiveDamage(player.damage)

        assertThat(resultingBoss.hitPoints, equalTo(4))
    }

    @Test
    internal fun `5 damage does 1 hp of damage to armor of 1000`() {
        val player = Character("player", damage = 5)
        val boss = Character("boss", armor = 1000, hitPoints = 5)

        val resultingBoss = boss.receiveDamage(player.damage)

        assertThat(resultingBoss.hitPoints, equalTo(4))
    }
}