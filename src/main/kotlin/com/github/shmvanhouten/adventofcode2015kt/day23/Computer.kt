package com.github.shmvanhouten.adventofcode2015kt.day23

class Computer(val instructions: List<Instruction> = emptyList()) {
    constructor(instructions: String) : this(parseInstructions(instructions))

    private val registers = Register.values().associateWith { 0L }.toMutableMap()
    val a: Long
        get() = registers[Register.A]!!
    val b: Long
        get() = registers[Register.B]!!

    fun execute() {
        var pointer = 0
        while (pointer < instructions.size) {
            val instruction = instructions[pointer]
            if (instruction.type.isAnEvalType()) {
                registers[instruction.register]!!
                    .let { instruction.type.evaluate(it) }
                    .let { registers[instruction.register!!] = it }
                pointer++
            } else if (instruction.type == InstructionType.JIE) {
                if (registers[instruction.register]!!.isEven()) {
                    pointer += instruction.amount!!
                } else {
                    pointer++
                }
            } else {
                pointer += instruction.amount!!
            }
        }
    }
}

private fun Long.isEven(): Boolean {
    return this % 2 == 0L
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
        InstructionType.JIE -> Instruction(type, Register.valueOf(split[1].toUpperCase()), toJumpAmount(split[2]))
    }
}

fun toJumpAmount(raw: String): Int {
    if (raw[0] == '+') {
        return raw.substring(1).toInt()
    } else {
        return raw.toInt()
    }
}

private fun String.toType(): InstructionType {
    return InstructionType.valueOf(this.toUpperCase())
}
