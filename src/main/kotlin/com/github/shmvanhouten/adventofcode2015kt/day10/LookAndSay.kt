package com.github.shmvanhouten.adventofcode2015kt.day10


fun lookAndSay(input: String, times: Int): String {
    return 0.until(times).fold(input) { look, _ -> lookAndSay(look) }
}

//fun lookAndSay(input: String): String { this is super slow
//    var remaining = input
//    val result = StringBuilder()
//
//    while (remaining.isNotEmpty()) {
//        val char = remaining[0]
//        val occurrence = remaining.takeWhile { it == char }.length
//        result.append(occurrence)
//        result.append(char)
//        remaining = remaining.substring(occurrence)
//    }
//    return result.toString()
//}

fun lookAndSay(input: String): String {
    val result = StringBuilder()
    var prevChar = input[0]
    var occurrence = 0
    for (c in input) {
        if(c == prevChar) {
            occurrence++
        } else {
            result.append(occurrence)
            result.append(prevChar)
            occurrence = 1
            prevChar = c
        }
    }
    result.append(occurrence)
    result.append(prevChar)

    return result.toString()
}