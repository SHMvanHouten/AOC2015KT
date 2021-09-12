package com.github.shmvanhouten.adventofcode2015kt.day13

import com.github.shmvanhouten.adventofcode2015kt.util.getAllRoutes
import com.github.shmvanhouten.adventofcode2020.util.repeat

fun findOptimalHappiness(guests: List<Guest>): Happiness {
    val seatingArrangements = getAllRoutes(guests)
    return seatingArrangements
        .map { calculateHappiness(it) }
        .maxOrNull() ?: throw RuntimeException("could not find minimum happiness for guests $guests")

}

fun calculateHappiness(seatingArrangement: List<Guest>): Happiness {
    return seatingArrangement.asSequence().repeat()
        .windowed(3)
        .take(seatingArrangement.size)
        .sumBy { calculateHappinessForMiddleGuest(it) }
}

fun calculateHappinessForMiddleGuest(guestTriplets: List<Guest>): Happiness {
    val middleGuest = guestTriplets[1]
    return middleGuest.feelingsTowards(guestTriplets[0]) +
            middleGuest.feelingsTowards(guestTriplets[2])
}
