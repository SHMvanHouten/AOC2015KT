package com.github.shmvanhouten.adventofcode2015kt.day9

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class TravelingSalesmanTest {

    @Test
    internal fun `the shortest distance for a route of 2 points is that 20`() {
        val amsterdamToHaarlem = PairDistance("Amsterdam", "Haarlem", 20)

        val shortestDistance = findShortestDistance(amsterdamToHaarlem)

        assertThat(shortestDistance, equalTo(20))
    }

    @Test
    internal fun `the shortest distance for a route of 2 points is that 30`() {
        val amstelveenToHaarlem = PairDistance("Amstelveen", "Haarlem", 30)

        val shortestDistance = findShortestDistance(amstelveenToHaarlem)

        assertThat(shortestDistance, equalTo(30))
    }

    @Test
    internal fun `the shortest distance between three points is 30`() {
        val amsterdamToHaarlem = PairDistance("Amsterdam", "Haarlem", 20)
        val amsterdamToAmstelveen = PairDistance("Amsterdam", "Amstelveen", 10)
        val haarlemToAmstelveen = PairDistance("Haarlem", "Amstelveen", 30)

        val shortestDistance = findShortestDistance(amsterdamToHaarlem, amsterdamToAmstelveen, haarlemToAmstelveen)

        assertThat(shortestDistance, equalTo(30))

    }

    @Test
    internal fun `the shortest distance between these three points is 605`() {
        val londonToDublin = PairDistance("London", "Dublin", 464)
        val londonToBelfast = PairDistance("London", "Belfast", 518)
        val dublinToBelfast = PairDistance("Dublin", "Belfast", 141)

        val shortestDistance = findShortestDistance(londonToDublin, londonToBelfast, dublinToBelfast)

        assertThat(shortestDistance, equalTo(605))
    }

    @Test
    internal fun `parse input`() {
        val pairDistances: List<PairDistance> = parseInput(testinput)
        assertThat(pairDistances.size, equalTo(3))
        assertThat(pairDistances[0], equalTo(PairDistance("London", "Dublin", 464)))
        assertThat(pairDistances[1], equalTo(PairDistance("London", "Belfast", 518)))
        assertThat(pairDistances[2], equalTo(PairDistance("Dublin", "Belfast", 141)))
    }


    val testinput = """London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141"""
}