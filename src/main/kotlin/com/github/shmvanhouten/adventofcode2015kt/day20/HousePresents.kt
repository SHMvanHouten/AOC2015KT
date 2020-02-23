package com.github.shmvanhouten.adventofcode2015kt.day20

import com.github.shmvanhouten.adventofcode2015kt.factors.factors
import com.github.shmvanhouten.adventofcode2015kt.factors.factorsThatHaveNotBeenVisitedOver50Times

fun calculatePresents(houseNumber: Long): Long {
    return factors(houseNumber)
        .map { it * 10L }
        .sum()
}

fun firstHouseTo(number:Long) :Long {
        return (831600L..831600L).asSequence()
        .first { calculatePresents(it) >= number }
}

fun firstHouseTo2(number:Long) :Long {
        return (776160L..1006920L).asSequence()
        .first { calculatePresents2(it) >= number }
}

fun calculatePresents2(houseNumber: Long): Long {
    return factorsThatHaveNotBeenVisitedOver50Times(houseNumber)
        .map { it * 11L }
        .sum()
}
