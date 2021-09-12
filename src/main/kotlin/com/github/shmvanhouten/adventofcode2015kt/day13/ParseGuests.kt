package com.github.shmvanhouten.adventofcode2015kt.day13

import java.lang.RuntimeException


fun parseGuests(input: String): List<Guest> {
    return input.split('\n')
        .map { it.split(' ') }
        .groupBy { it[0] }
        .map { toGuest(it.key, it.value) }
}

fun toGuest(name: String, rawFeelings: List<List<String>>): Guest {
    val feelingsTowardOthers = toFeelingsTowardsOthers(rawFeelings)
    return Guest(name, feelingsTowardOthers)
}

fun toFeelingsTowardsOthers(rawFeelings: List<List<String>>): Map<String, Happiness> {
    return rawFeelings
        .map { dropFullStop(it.last()) to parseHappiness(it) }
        .toMap()
}

private fun dropFullStop(name: String) = name.dropLast(1)

fun parseHappiness(rawFeelings: List<String>): Happiness {
    val modifier = when (rawFeelings[2]) {
        "gain" -> 1
        "lose" -> -1
        else -> throw RuntimeException("unknown modifier ${rawFeelings[2]} in rawFeelings $rawFeelings")
    }
    return modifier * rawFeelings[3].toInt()
}

typealias Happiness = Int