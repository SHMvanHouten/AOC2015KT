package com.github.shmvanhouten.adventofcode2015kt.day13

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SeatingArrangementTest {

    @Nested
    inner class `Seating arrangement` {
        @Test
        internal fun `total happiness between two guests would be their hapinesses combined times 2 because they "surround" each other`() {
            givenFeelingsTowardEachOtherTotalHappinessIs(3, -54, -102)
            givenFeelingsTowardEachOtherTotalHappinessIs(4, 54, 116)
        }

        @Test
        internal fun `calculate total happiness between four guests`() {
            val alice = Guest("Alice", listOf("Bob" to 54, "Carol" to -79, "David" to -2).toMap())
            val bob = Guest("Bob", listOf("Alice" to 83, "Carol" to -7, "David" to -63).toMap())
            val carol = Guest("Carol", listOf("Alice" to -62, "Bob" to 60, "David" to 55).toMap())
            val david = Guest("David", listOf("Alice" to 46, "Bob" to -7, "Carol" to 41).toMap())

            val guests = listOf(alice, bob, carol, david)
            assertThat(findOptimalHappiness(guests), equalTo(330))
        }

        @Test
        internal fun `calculate total happiness for the test input`() {
            assertThat(findOptimalHappiness(parseGuests(input)), equalTo(709))
            assertThat(findOptimalHappiness(parseGuests(input2)), equalTo(668))
        }

        private fun givenFeelingsTowardEachOtherTotalHappinessIs(
            bobsFeeling: Int,
            alicesFeeling: Int,
            expectedHappiness: Int
        ) {
            val bob = Guest("Bob", listOf("Alice" to bobsFeeling).toMap())
            val alice = Guest("Alice", listOf("Bob" to alicesFeeling).toMap())

            assertThat(findOptimalHappiness(listOf(bob, alice)), equalTo(expectedHappiness))
        }
    }

    @Nested
    inner class `Parse guests` {
        @Test
        internal fun `Bob and Alice parse`() {
            val input = """Bob would gain 3 happiness units by sitting next to Alice.
Alice would lose 54 happiness units by sitting next to Bob."""

            val guests = parseGuests(input)
            val bob = guests[0]
            assertThat(bob.name, equalTo("Bob"))
            val bobsFeelings = bob.feelingsTowardOthers
            assertThat(bobsFeelings.size, equalTo(1))
            assertThat(bobsFeelings["Alice"], equalTo(3))

            val alice = guests[1]
            assertThat(alice.name, equalTo("Alice"))
            val alicesFeelings = alice.feelingsTowardOthers
            assertThat(alicesFeelings.size, equalTo(1))
            assertThat(alicesFeelings["Bob"], equalTo(-54))
        }

//        @Test
//        internal fun `print the test input`() {
//            println(parseGuests(testInput))
//          [Guest(name=Alice, feelingsTowardOthers={Bob=54, Carol=-79, David=-2}), Guest(name=Bob, feelingsTowardOthers={Alice=83, Carol=-7, David=-63}), Guest(name=Carol, feelingsTowardOthers={Alice=-62, Bob=60, David=55}), Guest(name=David, feelingsTowardOthers={Alice=46, Bob=-7, Caro=41})]
//        }
    }
}

val testInput = """Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 79 happiness units by sitting next to Carol.
Alice would lose 2 happiness units by sitting next to David.
Bob would gain 83 happiness units by sitting next to Alice.
Bob would lose 7 happiness units by sitting next to Carol.
Bob would lose 63 happiness units by sitting next to David.
Carol would lose 62 happiness units by sitting next to Alice.
Carol would gain 60 happiness units by sitting next to Bob.
Carol would gain 55 happiness units by sitting next to David.
David would gain 46 happiness units by sitting next to Alice.
David would lose 7 happiness units by sitting next to Bob.
David would gain 41 happiness units by sitting next to Carol"""

val input = """Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 81 happiness units by sitting next to Carol.
Alice would lose 42 happiness units by sitting next to David.
Alice would gain 89 happiness units by sitting next to Eric.
Alice would lose 89 happiness units by sitting next to Frank.
Alice would gain 97 happiness units by sitting next to George.
Alice would lose 94 happiness units by sitting next to Mallory.
Bob would gain 3 happiness units by sitting next to Alice.
Bob would lose 70 happiness units by sitting next to Carol.
Bob would lose 31 happiness units by sitting next to David.
Bob would gain 72 happiness units by sitting next to Eric.
Bob would lose 25 happiness units by sitting next to Frank.
Bob would lose 95 happiness units by sitting next to George.
Bob would gain 11 happiness units by sitting next to Mallory.
Carol would lose 83 happiness units by sitting next to Alice.
Carol would gain 8 happiness units by sitting next to Bob.
Carol would gain 35 happiness units by sitting next to David.
Carol would gain 10 happiness units by sitting next to Eric.
Carol would gain 61 happiness units by sitting next to Frank.
Carol would gain 10 happiness units by sitting next to George.
Carol would gain 29 happiness units by sitting next to Mallory.
David would gain 67 happiness units by sitting next to Alice.
David would gain 25 happiness units by sitting next to Bob.
David would gain 48 happiness units by sitting next to Carol.
David would lose 65 happiness units by sitting next to Eric.
David would gain 8 happiness units by sitting next to Frank.
David would gain 84 happiness units by sitting next to George.
David would gain 9 happiness units by sitting next to Mallory.
Eric would lose 51 happiness units by sitting next to Alice.
Eric would lose 39 happiness units by sitting next to Bob.
Eric would gain 84 happiness units by sitting next to Carol.
Eric would lose 98 happiness units by sitting next to David.
Eric would lose 20 happiness units by sitting next to Frank.
Eric would lose 6 happiness units by sitting next to George.
Eric would gain 60 happiness units by sitting next to Mallory.
Frank would gain 51 happiness units by sitting next to Alice.
Frank would gain 79 happiness units by sitting next to Bob.
Frank would gain 88 happiness units by sitting next to Carol.
Frank would gain 33 happiness units by sitting next to David.
Frank would gain 43 happiness units by sitting next to Eric.
Frank would gain 77 happiness units by sitting next to George.
Frank would lose 3 happiness units by sitting next to Mallory.
George would lose 14 happiness units by sitting next to Alice.
George would lose 12 happiness units by sitting next to Bob.
George would lose 52 happiness units by sitting next to Carol.
George would gain 14 happiness units by sitting next to David.
George would lose 62 happiness units by sitting next to Eric.
George would lose 18 happiness units by sitting next to Frank.
George would lose 17 happiness units by sitting next to Mallory.
Mallory would lose 36 happiness units by sitting next to Alice.
Mallory would gain 76 happiness units by sitting next to Bob.
Mallory would lose 34 happiness units by sitting next to Carol.
Mallory would gain 37 happiness units by sitting next to David.
Mallory would gain 40 happiness units by sitting next to Eric.
Mallory would gain 18 happiness units by sitting next to Frank.
Mallory would gain 7 happiness units by sitting next to George."""

val input2 = """Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 81 happiness units by sitting next to Carol.
Alice would lose 42 happiness units by sitting next to David.
Alice would gain 89 happiness units by sitting next to Eric.
Alice would lose 89 happiness units by sitting next to Frank.
Alice would gain 97 happiness units by sitting next to George.
Alice would lose 94 happiness units by sitting next to Mallory.
Alice would gain 0 happiness units by sitting next to Sjoerd.
Bob would gain 3 happiness units by sitting next to Alice.
Bob would lose 70 happiness units by sitting next to Carol.
Bob would lose 31 happiness units by sitting next to David.
Bob would gain 72 happiness units by sitting next to Eric.
Bob would lose 25 happiness units by sitting next to Frank.
Bob would lose 95 happiness units by sitting next to George.
Bob would gain 11 happiness units by sitting next to Mallory.
Bob would gain 0 happiness units by sitting next to Sjoerd.
Carol would lose 83 happiness units by sitting next to Alice.
Carol would gain 8 happiness units by sitting next to Bob.
Carol would gain 35 happiness units by sitting next to David.
Carol would gain 10 happiness units by sitting next to Eric.
Carol would gain 61 happiness units by sitting next to Frank.
Carol would gain 10 happiness units by sitting next to George.
Carol would gain 29 happiness units by sitting next to Mallory.
Carol would gain 0 happiness units by sitting next to Sjoerd.
David would gain 67 happiness units by sitting next to Alice.
David would gain 25 happiness units by sitting next to Bob.
David would gain 48 happiness units by sitting next to Carol.
David would lose 65 happiness units by sitting next to Eric.
David would gain 8 happiness units by sitting next to Frank.
David would gain 84 happiness units by sitting next to George.
David would gain 9 happiness units by sitting next to Mallory.
David would gain 0 happiness units by sitting next to Sjoerd.
Eric would lose 51 happiness units by sitting next to Alice.
Eric would lose 39 happiness units by sitting next to Bob.
Eric would gain 84 happiness units by sitting next to Carol.
Eric would lose 98 happiness units by sitting next to David.
Eric would lose 20 happiness units by sitting next to Frank.
Eric would lose 6 happiness units by sitting next to George.
Eric would gain 60 happiness units by sitting next to Mallory.
Eric would gain 0 happiness units by sitting next to Sjoerd.
Frank would gain 51 happiness units by sitting next to Alice.
Frank would gain 79 happiness units by sitting next to Bob.
Frank would gain 88 happiness units by sitting next to Carol.
Frank would gain 33 happiness units by sitting next to David.
Frank would gain 43 happiness units by sitting next to Eric.
Frank would gain 77 happiness units by sitting next to George.
Frank would lose 3 happiness units by sitting next to Mallory.
Frank would gain 0 happiness units by sitting next to Sjoerd.
George would lose 14 happiness units by sitting next to Alice.
George would lose 12 happiness units by sitting next to Bob.
George would lose 52 happiness units by sitting next to Carol.
George would gain 14 happiness units by sitting next to David.
George would lose 62 happiness units by sitting next to Eric.
George would lose 18 happiness units by sitting next to Frank.
George would lose 17 happiness units by sitting next to Mallory.
George would gain 0 happiness units by sitting next to Sjoerd.
Mallory would lose 36 happiness units by sitting next to Alice.
Mallory would gain 76 happiness units by sitting next to Bob.
Mallory would lose 34 happiness units by sitting next to Carol.
Mallory would gain 37 happiness units by sitting next to David.
Mallory would gain 40 happiness units by sitting next to Eric.
Mallory would gain 18 happiness units by sitting next to Frank.
Mallory would gain 7 happiness units by sitting next to George.
Mallory would gain 0 happiness units by sitting next to Sjoerd.
Sjoerd would lose 0 happiness units by sitting next to Alice.
Sjoerd would gain 0 happiness units by sitting next to Bob.
Sjoerd would lose 0 happiness units by sitting next to Carol.
Sjoerd would gain 0 happiness units by sitting next to David.
Sjoerd would gain 0 happiness units by sitting next to Eric.
Sjoerd would gain 0 happiness units by sitting next to Frank.
Sjoerd would gain 0 happiness units by sitting next to George.
Sjoerd would gain 0 happiness units by sitting next to Mallory."""