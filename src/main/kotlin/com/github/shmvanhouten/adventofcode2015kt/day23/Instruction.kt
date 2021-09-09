package com.github.shmvanhouten.adventofcode2015kt.day23


data class Instruction(
    val type: InstructionType,
    val register: Register? = null,
    val amount: Int? = null
)

private val EVAL_TYPES = setOf(
    InstructionType.INC,
    InstructionType.TPL,
    InstructionType.HLF
)
enum class InstructionType {
    INC,
    TPL,
    HLF,
    JMP;

    fun evaluate(input: Long): Long {
        return when(this) {
            INC -> input + 1
            TPL -> input * 3
            HLF -> input / 2
            else -> error("instruction $this does not have evaluate")
        }
    }


    fun isAnEvalType(): Boolean {
        return EVAL_TYPES.contains(this)
    }
}

enum class Register {
    A,
    B
}