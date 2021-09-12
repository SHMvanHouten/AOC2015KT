package com.github.shmvanhouten.adventofcode2015kt.day20

import com.github.shmvanhouten.adventofcode2015kt.factors.factors
import com.github.shmvanhouten.adventofcode2015kt.factors.factorsThatHaveNotBeenVisitedOver50Times

fun calculatePresents(houseNumber: Long): Long {
    return factors(houseNumber).sumOf { it * 10L }
}

fun firstHouseTo(number:Long) :Long {
        return (831600L..831600L)
        .first { calculatePresents(it) >= number }
}

fun firstHouseTo2(number:Long) :Long {
        return (776160L..1006920L)
        .first { calculatePresents2(it) >= number }
}

fun calculatePresents2(houseNumber: Long): Long {
    return factorsThatHaveNotBeenVisitedOver50Times(houseNumber)
        .sumOf { it * 11L }
}
