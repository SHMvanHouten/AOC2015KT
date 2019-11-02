package com.github.shmvanhouten.adventofcode2015kt.day10

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class LookAndSayTest {
//    1 becomes 11 (1 copy of digit 1)
//    11 becomes 21 (2 copies of digit 1)
//    21 becomes 1211 (one 2 followed by one 1)
//    1211 becomes 111221 (one 1, one 2, and two 1s)
//    111221 becomes 312211 (three 1s, two 2s, and one 1)

    @Test
    internal fun `1 becomes 11 (1 copy of digit 1)`() {
        assertThat(lookAndSay("1"), equalTo("11"))
    }

    @Test
    internal fun `2 becomes 12 (1 copiy of digit 2)`() {
        assertThat(lookAndSay("2"), equalTo("12"))
    }

    @Test
    internal fun `11 becomes 21 (2 copies of digit 1)`() {
        assertThat(lookAndSay("11"), equalTo("21"))
    }

    @Test
    internal fun `21 becomes 1211 (one 2, one 1)`() {
        assertThat(lookAndSay("21"), equalTo("1211"))
    }

    @Test
    internal fun `111221 becomes 312211 (three 1s, two 2s, and one 1)`() {
        assertThat(lookAndSay("111221"), equalTo("312211"))
    }

    @Test
    internal fun `1 lookAndSayed 5 times becomes 312211`() {
        assertThat(lookAndSay("1", 5), equalTo("312211"))
    }

    @Test
    internal fun `1 lookandSayed 4 times becomes 111221`() {
        assertThat(lookAndSay("1", 4), equalTo("111221"))
    }

    @Test
    internal fun `part 1`() {
//        println(lookAndSay("1113222113", 40).length)
        // 252594
    }

    @Test
    internal fun `part 2`() {
        println(lookAndSay("1113222113", 45).length)
    }
}