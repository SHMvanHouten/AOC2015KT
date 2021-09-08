package com.github.shmvanhouten.adventofcode2015kt.day19

import com.github.shmvanhouten.adventofcode2015kt.util.FileReader.readFile
import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo
import com.github.shmvanhouten.adventofcode2020.util.blocks
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MoleculeReplacementTest {

    @Nested
    inner class MoleculeReplacement {

        @Test
        internal fun `given a dictionary with H - HO, H becomes HO`() {
            val dictionary: Dictionary = mapOf("H" to listOf(listOf("H", "O")))

            val result = listReplacements("H", dictionary)

            assertThat(result, equalTo(listOf("HO")))
        }

        @Test
        internal fun `given a dictionary with O - HO, O becomes OH`() {
            val dictionary: Dictionary = mapOf("O" to listOf(listOf("O", "H")))

            val result = listReplacements("O", dictionary)

            assertThat(result, equalTo(listOf("OH")))
        }

        @Test
        internal fun `given a dictionary with A - AO, A becomes AO`() {
            val dictionary: Dictionary = mapOf("A" to listOf(listOf("A", "O")))

            val result = listReplacements("A", dictionary)

            assertThat(result, equalTo(listOf("AO")))
        }

        @Test
        internal fun `given a dictionary with A - AO and H - HO, H becomes HO`() {
            val dictionary: Dictionary = mapOf("A" to listOf(listOf("A", "O")), "H" to listOf(listOf("H", "O")))

            val result = listReplacements("H", dictionary)

            assertThat(result, equalTo(listOf("HO")))
        }

        @Test
        internal fun `given a dictionary with A - AO and H - HO, HA becomes HOA or HAO`() {
            val dictionary: Dictionary = mapOf("A" to listOf(listOf("A", "O")), "H" to listOf(listOf("H", "O")))

            val result = listReplacements("HA", dictionary)

            assertThat(result, equalTo(listOf("HOA", "HAO")))
        }

        @Test
        internal fun `a molecule like Ac counts as one`() {
            val dictionary: Dictionary = mapOf("Ac" to listOf(listOf("Ac", "B")))

            val result = listReplacements("Ac", dictionary)

            assertThat(result, equalTo(listOf("AcB")))
        }

        @Nested
        inner class `Double entries in the dictionary` {
            @Test
            internal fun `given a dictionary with H - AA and H - HO, H becomes AA or HO`() {
                val dictionary: Dictionary = mapOf("H" to listOf(listOf("A", "A"), listOf("H", "O")))

                val result = listReplacements("H", dictionary)

                assertThat(result, equalTo(listOf("AA", "HO")))
            }

        }

        @Test
        internal fun `testInput - replacements should be distinct`() {
            val (dictionary, molecule) = Parser().toDictionaryAndInputMolecule("/test-input-day19.txt")

            val replacements = listReplacements(molecule, dictionary)

            assertThat(replacements.sorted(), equalTo(listOf("HOOH", "HOHO", "OHOH", "HHHH").sorted()))
        }

        @Test
        internal fun `testInput - but molecule is HOHOHO`() {
            val (dictionary, _) = Parser().toDictionaryAndInputMolecule("/test-input-day19.txt")

            val replacements = listReplacements("HOHOHO", dictionary)

            assertThat(replacements.size, equalTo(7))
        }

        @Test
        internal fun `part 1`() {
            val (dictionary, molecule) = Parser().toDictionaryAndInputMolecule("/input-day19.txt")

            val replacements = listReplacements(molecule, dictionary)

            println(replacements.size)
        }

        @Test
        internal fun `count unique atoms`() {
            val (dictionary, _) = Parser().toDictionaryAndInputMolecule("/input-day19.txt")
            println((dictionary.keys + dictionary.values.map { it.flatten() }.flatten()).distinct())
            println((dictionary.keys + dictionary.values.map { it.flatten() }.flatten()).distinct().count())
        }
    }

    @Nested
    inner class Part_2 {

        @Test
        internal fun example() {
            val targetMolecule = "HOHOHO"
            val replacements = """
                e => H
                e => O
                H => HO
                H => OH
                O => HH
            """.trimIndent()
            val dictionary = replacements.lines().map { it.splitIntoTwo(" => ") }

            assertThat(
                countStepsToTargetMolecule(targetMolecule, dictionary),
                equalTo(6)
            )
        }

        @Test
        internal fun `part 2`() {
            val blocks = readFile("/input-day19.txt").blocks()
            val dictionary = blocks[0].lines().map { it.splitIntoTwo(" => ") }
            val targetMolecule = blocks[1]

            assertThat(
                countStepsToTargetMolecule(targetMolecule, dictionary),
                equalTo(200)
            )
        }
    }
}