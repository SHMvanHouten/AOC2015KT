package com.github.shmvanhouten.adventofcode2015kt.day25

import com.github.shmvanhouten.adventofcode2015kt.util.Coordinate
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day25Test {

    @Nested
    inner class Given_the_top_left_code_is_20151125 {

        @Test
        internal fun `the top left code is 20151125`() {
            assertThat(
                codeAt(Coordinate(1,1)),
                equalTo(20151125L)
            )
        }

        @Test
        internal fun `then the next code at 1 2 will be 31916031`() {
            assertThat(
                codeAt(Coordinate(1,2)),
                equalTo(31916031)
            )
        }

        @Test
        internal fun `then the next code at 2 1 will be 18749137`() {
            assertThat(
                codeAt(Coordinate(2,1)),
                equalTo(18749137)
            )
        }

        @Test
        internal fun `then the next code at 6 6 will be 27995004`() {
            assertThat(
                codeAt(Coordinate(6,6)),
                equalTo(27995004)
            )
        }

        @Test
        internal fun `part 1`() {
            assertThat(
                codeAt(Coordinate(3029, 2947)),
                equalTo(19980801)
            )
        }
    }

}
