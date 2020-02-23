package com.github.shmvanhouten.adventofcode2015kt.factors

//import com.github.shmvanhouten.adventofcode2015kt.util.splitIntoTwo

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

//fun factors(number: Long): List<Long> {
//    return primeFactors(number)
//        .listMultiplications()
//}
//
//private fun List<Long>.listMultiplications(): List<Long> {
//    return listOf(1L) +
//            0.until(this.size)
//                .flatMap { listMultiplications(primeFactors = this.subList(it, this.size)) }
//                .sorted()
//                .distinct()
//}
//
//fun listMultiplications(l: Long = 1L, primeFactors: List<Long>, factors: List<Long> = emptyList()): List<Long> {
//    if (primeFactors.isEmpty()) {
//        return factors
//    }
//    val (head, tail) = primeFactors.splitIntoTwo(1)
//    val currentPrime = head[0] * l
//    return listMultiplications(currentPrime, tail, factors + currentPrime + tail.map { it * currentPrime })
//}

