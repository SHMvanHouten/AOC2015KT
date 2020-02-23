package com.github.shmvanhouten.adventofcode2015kt.factors

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class PrimeFactorsTest {

    @Test
    internal fun `prime factors of 2 is 2`() {
        assertThat(primeFactors(2L), equalTo(listOf(2L)))
    }

    @Test
    internal fun `prime factor of 3 is 3`() {
        assertThat(primeFactors(3L), equalTo(listOf(3L)))
    }

    @Test
    internal fun `prime factors of 4 are 2, 2`() {
        assertThat(primeFactors(4L), equalTo(listOf(2L, 2)))
    }

    @Test
    internal fun `prime factor of 5 is 5`() {
        assertThat(primeFactors(5L), equalTo(listOf(5L)))
    }

    @Test
    internal fun `prime factors of 6 are 2, 3`() {
        assertThat(primeFactors(6L), equalTo(listOf(2L, 3)))
    }

    @Test
    internal fun `prime factor of 7 is 7`() {
        assertThat(primeFactors(7L), equalTo(listOf(7L)))
    }

    @Test
    internal fun `prime factors of 8 are 2, 2, 2`() {
        assertThat(primeFactors(8L), equalTo(listOf(2L, 2, 2)))
    }

    @Test
    internal fun `prime factors of 9 are 3, 3`() {
        assertThat(primeFactors(9L), equalTo(listOf(3L, 3)))
    }

    @Test
    internal fun `all the prime factors`() {
        assertThat(
            primeFactors(2L * 2L * 3L * 7L * 13L * 101L),
            equalTo(listOf(2L, 2L, 3L, 7L, 13L, 101L))
        )
    }
}