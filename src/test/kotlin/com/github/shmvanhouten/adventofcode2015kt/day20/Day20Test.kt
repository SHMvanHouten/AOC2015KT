package com.github.shmvanhouten.adventofcode2015kt.day20

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day20Test {

    @ParameterizedTest(name = "house {0} gets {1} presents")
    @CsvSource(
        "1:10",
        "2:30",
        "3:40",
        "4:70",
        "5:60",
        "6:120",
        "7:80",
        "8:150",
        "9:130",
        "10000:242110",
        "1000000:24804370",
        delimiter = ':'
    )
    internal fun `house X gets Y presents`(houseNumber: Long, presents: Long) {
        assertThat(calculatePresents(houseNumber), equalTo(presents))
    }

    @Test
    internal fun `first house to 36000000 is 831600`() {
        assertThat(firstHouseTo(36000000L), equalTo(831600L))
    }

    @Test
    internal fun `part 2`() {
        assertThat(firstHouseTo2(36000000L), equalTo(884520L))
    }
}
