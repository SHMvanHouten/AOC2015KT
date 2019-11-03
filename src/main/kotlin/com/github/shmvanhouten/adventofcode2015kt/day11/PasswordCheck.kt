package com.github.shmvanhouten.adventofcode2015kt.day11

fun isValid(password: Password): Boolean {
    return !passwordHasForbiddenCharacters(password)
            && passwordHasAStraight(password)
            && passwordHasTwoNonOverlappingUniquePairs(password)
}

fun passwordHasTwoNonOverlappingUniquePairs(password: Password): Boolean {
    val sequences = password.findSequences()
    return sequences.size >= 2
            || sequences.any { it.recurrence >= 4 }
}


private fun String.findSequences(): List<CharRecurrence> {
    val matchSubsequentlyRecurringChars = Regex("(.)\\1+")
    return matchSubsequentlyRecurringChars.findAll(this)
        .map { toCharRecurrence(it) }
        .toList()
}

fun toCharRecurrence(matchResult: MatchResult): CharRecurrence {
    val result = matchResult.value
    return CharRecurrence(result[0], result.length)
}

fun passwordHasAStraight(password: Password): Boolean {
    var straightLength = 1
    var prevChar = '_'
    password.forEach { c ->
        if (c.follows(prevChar)) {
            straightLength++
            if (straightLength == 3) {
                return true
            }
        } else {
            straightLength = 1
        }
        prevChar = c
    }
    return false
}

private fun LowerCase.follows(prevChar: Char): Boolean {
    return this != 'a' && this == prevChar.next()
}

data class CharRecurrence(val char: Char, val recurrence: Int)

private fun passwordHasForbiddenCharacters(password: Password) =
    password.contains(Regex("[iol]"))