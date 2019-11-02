package com.github.shmvanhouten.adventofcode2015kt.day10


fun lookAndSay(input: String, times: Int): String {
    return 0.until(times).fold(input) { look, _ -> lookAndSay(look) }
}

fun lookAndSay(input: String): String {
    var remaining = input
    val result = StringBuilder()
    while (remaining.isNotEmpty()) {
        val char = remaining[0]
        val occurrence = remaining.takeWhile { it == char }.length
        result.append(occurrence)
        result.append(char)
        remaining = remaining.substring(occurrence)
    }
    return result.toString()
}