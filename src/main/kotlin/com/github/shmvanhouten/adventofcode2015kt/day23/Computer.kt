package com.github.shmvanhouten.adventofcode2015kt.day23

class Computer(val instructions: List<Instruction> = emptyList()) {
    constructor(instructions: String): this(parseInstructions(instructions))
    private val registers = Register.values().associateWith { 0L }.toMutableMap()
    val a: Long
        get() = registers[Register.A]!!
    val b: Long
        get() = registers[Register.B]!!

    fun execute() {
        instructions.forEach { instruction ->
            if(instruction.type == InstructionType.INC) {
                registers[instruction.register]!!.inc()
                    .let { registers[instruction.register!!] = it }
            } else {
                registers[instruction.register]!!.times(3)
                    .let { registers[instruction.register!!] = it }
            }
        }
    }
}

private fun parseInstructions(instructions: String): List<Instruction> {
    return instructions.lines()
        .map { toInstruction(it) }
}

fun toInstruction(raw: String) : Instruction {
    val split = raw.split(" ")
    return when(val type = split[0].toType()) {
        InstructionType.INC, InstructionType.TPL -> Instruction(type, Register.valueOf(split[1].toUpperCase()))
    }
}

private fun String.toType(): InstructionType {
    return when(this) {
        "inc" -> InstructionType.INC
        "tpl" -> InstructionType.TPL
        else -> error("unknown instruction $this")
    }
}
