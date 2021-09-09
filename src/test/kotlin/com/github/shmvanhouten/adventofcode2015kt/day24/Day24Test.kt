package com.github.shmvanhouten.adventofcode2015kt.day24

import com.github.shmvanhouten.adventofcode2015kt.util.FileReader.readFile
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day24Test {

    @Nested
    inner class Part1 {

        @Test
        internal fun `divides the group up into three even distributions`() {
            val packages = """
                1
                2
                3
                4
                5
                6
            """.trimIndent()

            assertThat(
                packageDistributions(packages).toSet(),
                equalTo(
                    setOf(
                        Distribution(listOf(1, 6), listOf(2, 5), listOf(3, 4)),
                        Distribution(listOf(1, 6), listOf(3, 4), listOf(2, 5)),
                        Distribution(listOf(2, 5), listOf(1, 6), listOf(3, 4)),
                        Distribution(listOf(2, 5), listOf(3, 4), listOf(1, 6)),
                        Distribution(listOf(3, 4), listOf(2, 5), listOf(1, 6)),
                        Distribution(listOf(3, 4), listOf(1, 6), listOf(2, 5)),
                    )
                )
            )
        }

        @Test
        internal fun `part 2 can it handle the recursion`() {
            // not enough time in the universe
            val packages = readFile("/input-day24.txt")
//            println(packageDistributions(packages).size)
        }

        @Test
        internal fun `example 1`() {
            val packages = """
                1
                2
                3
                4
                5
                7
                8
                9
                10
                11
            """.trimIndent()

            val bestFirstPackageGroup = findBestFirstPackageGroup(packages)
            assertThat(
                bestFirstPackageGroup.map { it.toLong() }.reduce(Long::times),
                equalTo(99)
            )
        }

        @Test
        internal fun `part 1`() {
            val packages = readFile("/input-day24.txt")
            val bestFirstPackageGroup = findBestFirstPackageGroup(packages)
            assertThat(
                bestFirstPackageGroup.map { it.toLong() }.reduce(Long::times),
                equalTo(10723906903)
            )
        }
    }

}
