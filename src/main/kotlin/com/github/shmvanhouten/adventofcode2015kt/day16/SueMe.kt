package com.github.shmvanhouten.adventofcode2015kt.day16

import com.github.shmvanhouten.adventofcode2017.util.rawinstructionconverter.RawInstructionConverter
import com.github.shmvanhouten.adventofcode2017.util.splitIntoTwo

val SUE_COMPOUNDS = listOf(
    "children" to 3,
    "cats" to 7,
    "samoyeds" to 2,
    "pomeranians" to 3,
    "akitas" to 0,
    "vizslas" to 0,
    "goldfish" to 5,
    "trees" to 3,
    "cars" to 2,
    "perfumes" to 1
).toMap()

fun main() {

    val parseSues = parseSues()
    val ourSue = parseSues.find { matchesSueCompounds(it) }
    println(ourSue)
    // 103

    val ourSue2 = parseSues.find { matchesSueCompounds2(it) }
    println(ourSue2)
    // 405
}

fun matchesSueCompounds2(sue: Sue): Boolean {
    return sue.compounds
        .all { sameCompounds(it) }
}

private fun sameCompounds(it: Map.Entry<String, Int>): Boolean {

    val compound = it.key
    val amount = SUE_COMPOUNDS[compound]?: error("unknown compound: $compound")
    return when(compound) {
        "cats", "trees" -> it.value > amount
        "pomeranians", "goldfish" -> it.value < amount
        else -> amount == it.value
    }
}

fun matchesSueCompounds(sue: Sue): Boolean {
    return sue.compounds
        .all { SUE_COMPOUNDS[it.key] == it.value }
}

fun parseSues(): List<Sue> {
    return RawInstructionConverter().convertRawInputIntoInstructions("/input-day16.txt", ::toSue)
}

fun toSue(rawSue: String): Sue {
    val name = rawSue.substring(0, rawSue.indexOf(':'))
    val rawCompounds = rawSue.substring(rawSue.indexOf(':') + 2).split(", ")
    val compounds = rawCompounds
        .map { it.splitIntoTwo(": ") }
        .map { it.first to it.second.toInt() }
        .toMap()
    return Sue(name, compounds)
}

data class Sue(val name: String, val compounds: Map<String, Int>)