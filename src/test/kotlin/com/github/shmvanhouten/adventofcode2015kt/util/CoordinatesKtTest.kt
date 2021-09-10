package com.github.shmvanhouten.adventofcode2015kt.util

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CoordinatesKtTest {
    @Nested
    inner class Diagonal_sequence_from {
        @Test
        internal fun `1st is at 0,0`() {
            assertThat(
                diagonalSequenceFrom(Coordinate(0, 0)).first(),
                equalTo(Coordinate(0, 0))
            )
        }

/*
   | 0   1   2   3   4   5
---+---+---+---+---+---+---+
 0 |  1   3   6  10  15  21
 1 |  2   5   9  14  20
 2 |  4   8  13  19
 3 |  7  12  18
 4 | 11  17
 5 | 16
 */

        @ParameterizedTest
        @CsvSource(
            value = [
                "1, 0, 0",
                "2, 0, 1",
                "3, 1, 0",
                "7, 0, 3",
                "13, 2, 2",
                "17, 1, 4",
                "21, 5, 0"

            ]
        )
        internal fun `diagonal sequence from bottom left to top right`(
            number: Int,
            x: Int,
            y: Int
        ) {
            assertThat(
                diagonalSequenceFrom(Coordinate(0, 0)).take(number).last(),
                equalTo(Coordinate(x, y))
            )
        }
    }
}