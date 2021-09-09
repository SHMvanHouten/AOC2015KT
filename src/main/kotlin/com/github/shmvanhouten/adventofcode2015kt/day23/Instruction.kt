package com.github.shmvanhouten.adventofcode2015kt.day23


data class Instruction(
    val type: InstructionType,
    val register: Register? = null,
    val amount: Int? = null
)

enum class InstructionType {
    INC,
    TPL,
    HLF;

    fun evaluate(input: Long): Long {
        return when(this) {
            INC -> input + 1
            TPL -> input * 3
            HLF -> input / 2
        }
    }
}

enum class Register {
    A,
    B
}