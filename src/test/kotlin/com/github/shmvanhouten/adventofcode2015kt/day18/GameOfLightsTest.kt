package com.github.shmvanhouten.adventofcode2015kt.day18

import com.github.shmvanhouten.adventofcode2015kt.util.FileReader.readFile
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class GameOfLightsTest {

    @Test
    internal fun `all lights remain off if all lights are off`() {
        val input = """..
                             |..""".trimMargin()
        val field = parseInput(input)

        // WHEN
        val result = tick(field)

        // THEN
        val expected = """..
                             |..""".trimMargin()

        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun `all lights turn off if only one light is on`() {
        val input = """.#
                             |..""".trimMargin()
        val field = parseInput(input)

        // WHEN
        val result = tick(field)

        // THEN
        val expected = """..
                             |..""".trimMargin()

        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun `a light will turn on if surrounded by exactly 3 lights, the other lights will remain on because they are close to two lights themselves`() {
        val input = """.#
                             |##""".trimMargin()
        val field = parseInput(input)

        // WHEN
        val result = tick(field)

        // THEN
        val expected = """##
                             |##""".trimMargin()

        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun `a light will stay off if surrounded by only 2 lights, and those lights will turn off because they are surrounded by 1 themselves`() {
        val input = """.#
                             |#.""".trimMargin()
        val field = parseInput(input)

        // WHEN
        val result = tick(field)

        // THEN
        val expected = """..
                             |..""".trimMargin()

        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun `a light will stay on if surrounded by 3 other lights that are on`() {
        val input = """##
                             |##""".trimMargin()
        val field = parseInput(input)

        // WHEN
        val result = tick(field)

        // THEN
        val expected = """##
                                |##""".trimMargin()

        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun `a light will stay off if surrounded by 4 lights or more, a light will turn off if adjacent to only 1 light`() {
        val input = """#.#
                             |##.
                             |...""".trimMargin()
        val field = parseInput(input)

        // WHEN
        val result = tick(field)

        // THEN
        val expected = """#..
                                |##.
                                |...""".trimMargin()

        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun `after 4 draws (test input)`() {
        val input = """
            |.#.#.#
            |...##.
            |#....#
            |..#...
            |#.#..#
            |####..""".trimMargin()

        // WHEN
        val result = tick(parseInput(input), 4)

        // THEN
        val expected = """
            |......
            |......
            |..##..
            |..##..
            |......
            |......""".trimMargin()
        assertThat(result.toString(), equalTo(expected))
    }

    @Test
    internal fun part1() {
        val field = parseInput(readFile("/input-day18.txt"))

        // WHEN
        val result = tick(field, 100)

        // THEN
        println(result.countLights())
        // 814
    }

    @Test
    internal fun `part 2`() {
        val field = parseInput(readFile("/input-day18.txt"))

        // WHEN
        val result = tickWithLightsStuck(field, 100)

        // THEN
        println(result.countLights())
    }
}