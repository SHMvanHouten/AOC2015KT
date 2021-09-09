package com.github.shmvanhouten.adventofcode2015kt.day22

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day22Test {

    @Nested
    inner class Part1 {

        @Test
        internal fun `poison deals damage each turn for 6 turns`() {
            var battleGround = BattleGround(Player(hitPoints = 1000), Boss(1, 100))
            battleGround = battleGround.passTurn(Effect.POISON)
            assertThat(battleGround.boss.hitPoints, equalTo(100))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.boss.hitPoints, equalTo(97))

            battleGround = battleGround.passTurn(Effect.SHIELD)
            assertThat(battleGround.boss.hitPoints, equalTo(94))

            battleGround = battleGround.passTurn()
            battleGround = battleGround.passTurn(Effect.SHIELD)
            battleGround = battleGround.passTurn()
            battleGround = battleGround.passTurn(Effect.SHIELD)
            assertThat(battleGround.boss.hitPoints, equalTo(82))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.boss.hitPoints, equalTo(82))
        }

        @Test
        internal fun `magic missile does an instant 4 damage to the boss`() {
            var battleGround = BattleGround(Player(hitPoints = 1000), Boss(1, 100))
            battleGround = battleGround.passTurn(Effect.MAGIC_MISSILE)
            assertThat(battleGround.boss.hitPoints, equalTo(96))
        }

        @Test
        internal fun `drain does instant 2 damage to the boss, and heals you for 2 damage`() {
            var battleGround = BattleGround(Player(hitPoints = 1000), Boss(0, 100))
            battleGround = battleGround.passTurn(Effect.DRAIN)
            assertThat(battleGround.boss.hitPoints, equalTo(98))
            // todo: can we heal over the starting hp?
            assertThat(battleGround.player.hitPoints, equalTo(1002))
        }

        @Test
        internal fun `recharge increases mana by 101 over 5 turns`() {
            var battleGround = BattleGround(Player(mana = 1000), Boss(0, 100))
            battleGround = battleGround.passTurn(Effect.RECHARGE)
            assertThat(battleGround.player.mana, equalTo(1000 - 229))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.player.mana, equalTo(771 + 101))

            battleGround = battleGround.passTurn(Effect.MAGIC_MISSILE)
            assertThat(battleGround.player.mana, equalTo(872 - 53 + 101))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.player.mana, equalTo(920 + 101))

            battleGround = battleGround.passTurn(Effect.MAGIC_MISSILE)
            assertThat(battleGround.player.mana, equalTo(1021 - 53 + 101))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.player.mana, equalTo(1069 + 101))

            battleGround = battleGround.passTurn(Effect.MAGIC_MISSILE)
            assertThat(battleGround.player.mana, equalTo(1170 - 53))
        }

        @Test
        internal fun `the boss deals damage every boss turn`() {
            var battleGround = BattleGround(Player(hitPoints = 1000), Boss(10, 100))

            battleGround = battleGround.passTurn(Effect.MAGIC_MISSILE)
            assertThat(battleGround.player.hitPoints, equalTo(1000))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.player.hitPoints, equalTo(990))
        }

        @Test
        internal fun `shield increases armor by 7 for 6 turns, reducing damage taken by the boss by 7`() {
            var battleGround = BattleGround(Player(hitPoints = 1000), Boss(10, 100))

            battleGround = battleGround.passTurn(Effect.SHIELD)
            assertThat(battleGround.player.hitPoints, equalTo(1000))

            battleGround = battleGround.passTurn()
            assertThat(battleGround.player.hitPoints, equalTo(997))
        }

        @ParameterizedTest
        @CsvSource(
            value = [
                "MAGIC_MISSILE, 53",
                "DRAIN, 73",
                "SHIELD, 113",
                "POISON, 173",
                "RECHARGE, 229"
            ]
        )
        internal fun `each spell costs mana`(
            effect: Effect,
            manaCost: Int
        ) {
            assertThat(
                BattleGround(Player(mana = 300), Boss(0, 100)).passTurn(effect).player.mana,
                equalTo(300 - manaCost)
            )
        }

        @Test
        internal fun `part 1`() {
            val hitPoints = 58
            val damage = 9
            assertThat(
                findMostManaEfficientBattle(Player(), Boss(damage, hitPoints)),
                equalTo(123)
            )
        }
    }

}
