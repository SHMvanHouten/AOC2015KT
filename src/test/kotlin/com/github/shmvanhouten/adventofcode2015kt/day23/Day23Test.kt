package com.github.shmvanhouten.adventofcode2015kt.day23

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day23Test {

    @Nested
    inner class Part1 {

        @Nested
        inner class Given_a_new_computer {

            @Test
            internal fun `computer starts with a = 0 and b = 0`() {
                val computer = Computer()
                assertThat(computer.a, equalTo(0))
                assertThat(computer.b, equalTo(0))
            }

            @Test
            internal fun `inc a sets register a to 1`() {
                val computer = Computer("inc a")
                computer.execute()
                assertThat(computer.a, equalTo(1))
            }

            @Test
            internal fun `inc b sets register b to 1`() {
                val computer = Computer("inc b")
                computer.execute()
                assertThat(computer.b, equalTo(1))
            }

            @Test
            internal fun `inc a twice sets register a to 2`() {
                val instructions = """inc a
                    |inc a
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.a, equalTo(2))
            }

            @Test
            internal fun `inc b twice sets register b to 2`() {
                val instructions = """inc b
                    |inc b
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.b, equalTo(2))
            }

            @Test
            internal fun `inc a then inc b sets both registers to 1`() {
                val instructions = """inc a
                    |inc b
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.a, equalTo(1))
                assertThat(computer.b, equalTo(1))
            }

            @Test
            internal fun `inc a then tpl a sets a to 3`() {
                val instructions = """inc a
                    |tpl a
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.a, equalTo(3))
            }

            @Test
            internal fun `inc b then tpl b sets b to 3`() {
                val instructions = """inc b
                    |tpl b
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.b, equalTo(3))
            }

            @Test
            internal fun `inc a inc a then hlf a sets a to 1`() {
                val instructions = """inc a
                    |inc a
                    |hlf a
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.a, equalTo(1))
            }

            @Test
            internal fun `jmp offset + 1 jumps to the next instruction`() {
                val instructions = """jmp +1
                    |inc a
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.a, equalTo(1))
            }

            @Test
            internal fun `jmp offset +2 jumps to the next instruction`() {
                val instructions = """jmp +2
                    |inc a
                    |inc b
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.a, equalTo(0))
                assertThat(computer.b, equalTo(1))
            }

            @Test
            internal fun `jie a does not jump if a is uneven`() {
                val instructions = """inc a
                    |jie a +2
                    |inc b
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.b, equalTo(1))
            }
            @Test
            internal fun `jie a + 2 jumps 2 if a is even`() {
                val instructions = """inc a
                    |inc a
                    |jie a +2
                    |inc b
                """.trimMargin()
                val computer = Computer(instructions)
                computer.execute()
                assertThat(computer.b, equalTo(0))
            }
        }
    }

}
