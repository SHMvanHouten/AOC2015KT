package com.github.shmvanhouten.adventofcode2015kt.day13

import com.github.shmvanhouten.adventofcode2015kt.util.getAllRoutes
import java.lang.RuntimeException

fun findOptimalHappiness(guests: List<Guest>): Happiness {
    val seatingArrangements = getAllRoutes(guests)
    return seatingArrangements
        .map { calculateHappiness(it) }
        .maxOrNull() ?: throw RuntimeException("could not find minimum happiness for guests $guests")

}

fun calculateHappiness(seatingArrangement: List<Guest>): Happiness {
    return (listOf(seatingArrangement.last()) + seatingArrangement + seatingArrangement.first())
        .windowed(3)
        .sumBy { calculateHappinessForMiddleGuest(it) }
}

fun calculateHappinessForMiddleGuest(guestTriplets: List<Guest>): Happiness {
    val middleGuest = guestTriplets[1]
    return middleGuest.feelingsTowards(guestTriplets[0]) +
            middleGuest.feelingsTowardOthers[guestTriplets[2].name]!!
}
