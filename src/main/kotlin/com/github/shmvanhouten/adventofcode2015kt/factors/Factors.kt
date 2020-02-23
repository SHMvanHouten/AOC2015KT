package com.github.shmvanhouten.adventofcode2015kt.factors

fun factors(number: Long): List<Long> {
    var divisor = 1L
    val factors = mutableSetOf<Long>()
    var remainder = number
    while (remainder >= divisor) {
        if(number % divisor == 0L) {
            factors += divisor
            remainder = number / divisor
            factors += remainder
        }
        divisor++
    }
    return factors.sorted()
}


fun factorsThatHaveNotBeenVisitedOver50Times(number: Long): List<Long> {
    var divisor = 1L
    val factors = mutableSetOf<Long>()
    var remainder = number
    while (remainder >= divisor && divisor <= 50) {
        if(number % divisor == 0L) {
            remainder = number / divisor
            factors += remainder
            if(remainder <= 50) {
                factors += divisor
            }
        }
        divisor++
    }
    return factors.sorted()
}
