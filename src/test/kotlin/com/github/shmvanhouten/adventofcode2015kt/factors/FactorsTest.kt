package com.github.shmvanhouten.adventofcode2015kt.factors

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class FactorsTest {

    @Test
    internal fun `the factor of 1 is 1`() {
        assertThat(factors(1L), equalTo(listOf(1L)))
    }

    @Test
    internal fun `the factors of 2 are 1, 2`() {
        assertThat(factors(2L), equalTo(listOf(1L, 2)))
    }

    @Test
    internal fun `the factors of 3 are 1, 3`() {
        assertThat(factors(3L), equalTo(listOf(1L, 3)))
    }

    @Test
    internal fun `the factors of 4 are, 1, 2, 4`() {
        assertThat(factors(4L), equalTo(listOf(1L, 2, 4)))
    }


    @Test
    internal fun `the factors of 12 are 1, 2, 3, 4, 6, 12`() {
        assertThat(factors(12L), equalTo(listOf(1L, 2, 3, 4, 6, 12)))
    }

    @Test
    internal fun `the factors of 30 are 1, 2, 3, 5, 6, 10, 15, 30`() {
        assertThat(factors(30L), equalTo(listOf(1L, 2, 3, 5, 6, 10, 15, 30)))
    }

    @Test
    internal fun `more factors`() {
        assertThat(factors(2L * 2L * 3L * 5L), equalTo(listOf(1L, 2, 3, 4, 5, 6, 10, 12, 15, 20, 30, 60)))
    }
}