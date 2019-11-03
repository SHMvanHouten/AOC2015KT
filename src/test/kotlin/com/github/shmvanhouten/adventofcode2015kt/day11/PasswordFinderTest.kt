package com.github.shmvanhouten.adventofcode2015kt.day11

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PasswordFinderTest {
//    Passwords must include one increasing straight of at least three letters,
//    like abc, bcd, cde, and so on, up to xyz.
//    They cannot skip letters; abd doesn't count.

//    Passwords may not contain the letters i, o, or l.

//    Passwords must contain at least two different,
//    non-overlapping pairs of letters,
//    like aa, bb, or zz.
    // so aaa should not pass this test

    @Nested
    inner class `Next password finder` {
        @Test
        internal fun `next password after abcdefgh is abcdffaa`() {
            assertThat(findNext("abcdefgh"), equalTo("abcdffaa"))
            assertThat(findNext("abcdffaa"), equalTo("abcdffbb"))
        }

        @Test
        internal fun `part 1`() {
            println(findNext("hxbxwxba"))
            //hxbxxyzz
        }

        @Test
        internal fun `part 2`() {
            println(findNext("hxbxxyzz"))
            //hxcaabcc
        }
    }

    @Nested
    inner class `Password validity check` {

        @Test
        internal fun `A password is valid if it has each of the three requirements`() {
            assertThat(isValid("abceeff"), equalTo(true))
            assertThat(isValid("efgeeff"), equalTo(true))
            assertThat(isValid("efgeeee"), equalTo(true))
        }

        @Test
        internal fun `A otherwise valid password but with an forbidden character is invalid`() {
            assertThat(isValid("abceeffi"), equalTo(false))
            assertThat(isValid("abceeffo"), equalTo(false))
            assertThat(isValid("abceeffl"), equalTo(false))
        }

        @Test
        internal fun `a otherwise valid password but without a three letter straight is invalid`() {
            assertThat(isValid("abeeff"), equalTo(false))
            // a does not follow z
            assertThat(isValid("zabeeff"), equalTo(false))
        }

        @Test
        internal fun `an otherwise valid password but without two different non-overlapping doubles is invalid`() {
            assertThat(isValid("abcre"), equalTo(false))
            assertThat(isValid("abc"), equalTo(false))
            assertThat(isValid("abcrere"), equalTo(false))
        }
    }

    @Nested
    inner class `Password iterator` {

        @Test
        internal fun `password iterator goes from a to b`() {
            assertThat("a".next(), equalTo("b"))
        }

        @Test
        internal fun `password iterator goes from b to c`() {
            assertThat("b".next(), equalTo("c"))
        }

        @Test
        internal fun `password iterator goes from c to d`() {
            assertThat("c".next(), equalTo("d"))
        }

        @Test
        internal fun `password iterator goes from z to a`() {
            assertThat("z".next(), equalTo("a"))
        }

        @Test
        internal fun `password iterator goes from aa to ab`() {
            assertThat("aa".next(), equalTo("ab"))
        }

        @Test
        internal fun `password iterator goes from abcdefgi to abcdefgj`() {
            assertThat("abcdefgi".next(), equalTo("abcdefgj"))
        }


        @Test
        internal fun `zz loops back to aa (not a requirement)`() {
            assertThat("zz".next(), equalTo("aa"))
        }
    }
}

