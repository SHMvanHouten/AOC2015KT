package com.github.shmvanhouten.adventofcode2015kt.day13

import com.github.shmvanhouten.adventofcode2015kt.util.getAllRoutes
import java.lang.RuntimeException

fun findOptimalHappiness(guests: List<Guest>): Happiness {
    val seatingArrangements = getAllRoutes(guests)
    return seatingArrangements
        .map { calculateHappiness(it) }
        .max() ?: throw RuntimeException("could not find minimum happiness for guests $guests")

}

fun calculateHappiness(seatingArrangement: List<Guest>): Happiness {
    return seatingArrangement.mapIndexed { i, guest ->
        getGuestsHappiness(guest, i, seatingArrangement)
    }.sum()
}

private fun getGuestsHappiness(
    guest: Guest,
    i: Int,
    seatingArrangement: List<Guest>
): Happiness {
    val (guest1, guest2) = getAdjacentGuests(i, seatingArrangement)
    val feelingsTowardOthers = guest.feelingsTowardOthers
    return getFeelingsTowardGuest(guest1, feelingsTowardOthers) +
            getFeelingsTowardGuest(guest2, feelingsTowardOthers)
}

private fun getFeelingsTowardGuest(
    guest1: Guest,
    feelingsTowardOthers: Map<String, Happiness>
) = (feelingsTowardOthers[guest1.name] ?: error("could not find guest ${guest1.name}"))

fun getAdjacentGuests(i: Int, seatingArrangement: List<Guest>): Pair<Guest, Guest> {
    return if(i == 0) {
        seatingArrangement[1] to seatingArrangement.last()
    } else if (i == seatingArrangement.size - 1) {
        seatingArrangement[i - 1] to seatingArrangement.first()
    } else {
        seatingArrangement[i - 1] to seatingArrangement[i + 1]
    }
}
