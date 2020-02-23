package com.github.shmvanhouten.adventofcode2015kt.day20

import com.github.shmvanhouten.adventofcode2015kt.factors.factors

fun calculatePresents(houseNumber: Long): Long {
    return factors(houseNumber)
        .map { it * 10L }
        .sum()
}

fun firstHouseTo(number:Long) :Long {
    return (831000L..10000000L).asSequence()
        .first { calculatePresents(it) >= number }
}