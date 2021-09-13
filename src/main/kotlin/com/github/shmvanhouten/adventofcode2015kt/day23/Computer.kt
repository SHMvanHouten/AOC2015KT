package com.github.shmvanhouten.adventofcode2015kt.day23

class Computer(val instructions: List<Instruction> = emptyList()) {
    constructor(instructions: String) : this(parseInstructions(instructions))

    private val registers = Register.values().associateWith { 0L }.toMutableMap()
    val a: Long by lazy { registers[Register.A]!! }
    val b: Long by lazy { registers[Register.B]!! }

    fun withARegisterAt(i: Long): Computer {
        registers[Register.A] = i
        return this
    }

    fun execute() {
        var pointer = 0
        while (pointer < instructions.size) {
            val instruction = instructions[pointer]
            when {
                instruction.type.isAnEvalType() -> {
                    updateRegister(instruction)
                    pointer++

                }
                instruction.type.isConditionalJumpType() -> {
                    pointer += evaluateJumpAmount(instruction)

                }
                else -> {
                    pointer += instruction.amount!!
                }
            }
        }
    }

    private fun updateRegister(instruction: Instruction) {
        registers[instruction.register]!!
            .let { instruction.type.evaluate(it) }
            .let { registers[instruction.register!!] = it }
    }

    private fun evaluateJumpAmount(instruction: Instruction) =
        if (registers[instruction.register]!!
                .let { instruction.type.evaluatesToTrue(it) }
        ) {
            instruction.amount!!
        } else 1
}


private fun parseInstructions(instructions: String): List<Instruction> {
    return instructions.lines()
        .map { toInstruction(it) }
}

fun toInstruction(raw: String): Instruction {
    val split = raw.split(" ")
    return when (val type = split[0].toType()) {
        InstructionType.INC,
        InstructionType.TPL,
        InstructionType.HLF -> Instruction(type, Register.valueOf(split[1].toUpperCase()))
        InstructionType.JMP -> Instruction(type = type, amount = toJumpAmount(split[1]))
        InstructionType.JIE,
        InstructionType.JIO -> Instruction(type, Register.valueOf(split[1].substring(0,1).toUpperCase()), toJumpAmount(split[2]))
    }
}

fun toJumpAmount(raw: String): Int {
    return if (raw[0] == '+') {
        raw.substring(1).toInt()
    } else {
        raw.toInt()
    }
}

private fun String.toType(): InstructionType {
    return InstructionType.valueOf(this.toUpperCase())
}
