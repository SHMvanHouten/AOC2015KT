package com.github.shmvanhouten.adventofcode2015kt.day23


data class Instruction(
    val type: InstructionType,
    val register: Register? = null,
    val amount: Int? = null
)

enum class InstructionType {
    INC,
    TPL,
    HLF
}

enum class Register {
    A,
    B
}