package com.github.shmvanhouten.adventofcode2015kt.day9

fun findShortestDistance(vararg pairDistances: PairDistance): Int {
    if(pairDistances.size == 1) {
        return pairDistances[0].distance
    } else {
        return pairDistances
            .map { it.distance }
            .sorted()
            .dropLast(1)
            .sum()
    }
}


fun parseInput(input: String): List<PairDistance> {
    return input.split('\n')
        .map { toPair(it) }
}

private fun toPair(raw: String): PairDistance {
    val split = raw.split(' ')
    return PairDistance(split[0], split[2], split[4].toInt())
}

data class PairDistance(val origin: String, val destination: String, val distance: Int)
