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

private val CONDITIONAL_JMP_TYPES = setOf(
    InstructionType.JIE,
    InstructionType.JIO
)

enum class InstructionType {
    INC,
    TPL,
    HLF,
    JMP,
    JIE,
    JIO;

    fun evaluate(input: Long): Long {
        return when(this) {
            INC -> input + 1
            TPL -> input * 3
            HLF -> input / 2
            else -> error("instruction $this does not have evaluate")
        }
    }


    fun evaluatesToTrue(input: Long): Boolean {
        return when(this) {
            JIE -> input.isEven()
            JIO -> input == 1L
            else -> error("instruction $this does not have a condition")
        }
    }

    fun isAnEvalType(): Boolean {
        return EVAL_TYPES.contains(this)
    }

    fun isConditionalJumpType(): Boolean {
        return CONDITIONAL_JMP_TYPES.contains(this)
    }
}

enum class Register {
    A,
    B
}

private fun Long.isEven(): Boolean {
    return this % 2 == 0L
}