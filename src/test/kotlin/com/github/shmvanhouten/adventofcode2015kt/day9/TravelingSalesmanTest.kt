package com.github.shmvanhouten.adventofcode2015kt.day9

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
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
    internal fun `between four points things can get a bit more tricky`() {
        /*
        Amsterdam -- 21 -- Haarlem
            |   \       /     |
            |    \     /      |
            50      80        40
            |   /       \     |
            |  /         \    |
        Utrecht --   19 -- Woerden
         */
        val amsterdamToHaarlem = PairDistance("Amsterdam", "Haarlem", 21)
        val amsterdamToWoerden = PairDistance("Amsterdam", "Woerden", 80)
        val amsterdamToUtrecht = PairDistance("Amsterdam", "Utrecht", 50)
        val haarlemToUtrecht = PairDistance("Haarlem", "Utrecht", 80)
        val haarlemToWoerden = PairDistance("Haarlem", "Woerden", 40)
        val UtrechtToWoerden = PairDistance("Utrecht", "Woerden", 19)

        val shortestDistance = findShortestDistance(
            amsterdamToHaarlem,
            amsterdamToWoerden,
            amsterdamToUtrecht,
            haarlemToUtrecht,
            haarlemToWoerden,
            UtrechtToWoerden
        )

        assertThat(shortestDistance, equalTo(21 + 40 + 19))
    }

    @Test
    internal fun `parse input`() {
        val pairDistances: List<PairDistance> = parseInput(testinput)
        assertThat(pairDistances.size, equalTo(3))
        assertThat(pairDistances[0], equalTo(PairDistance("London", "Dublin", 464)))
        assertThat(pairDistances[1], equalTo(PairDistance("London", "Belfast", 518)))
        assertThat(pairDistances[2], equalTo(PairDistance("Dublin", "Belfast", 141)))
    }

    @Test
    internal fun `part 1 and 2`() {
        val input = """AlphaCentauri to Snowdin = 66
AlphaCentauri to Tambi = 28
AlphaCentauri to Faerun = 60
AlphaCentauri to Norrath = 34
AlphaCentauri to Straylight = 34
AlphaCentauri to Tristram = 3
AlphaCentauri to Arbre = 108
Snowdin to Tambi = 22
Snowdin to Faerun = 12
Snowdin to Norrath = 91
Snowdin to Straylight = 121
Snowdin to Tristram = 111
Snowdin to Arbre = 71
Tambi to Faerun = 39
Tambi to Norrath = 113
Tambi to Straylight = 130
Tambi to Tristram = 35
Tambi to Arbre = 40
Faerun to Norrath = 63
Faerun to Straylight = 21
Faerun to Tristram = 57
Faerun to Arbre = 83
Norrath to Straylight = 9
Norrath to Tristram = 50
Norrath to Arbre = 60
Straylight to Tristram = 27
Straylight to Arbre = 81
Tristram to Arbre = 90"""
        val pairDistances = parseInput(input)
        println(findShortestDistance(pairDistances))
        // 141
        println(findLongestDistance(pairDistances))
        // 736
    }

    val testinput = """London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141"""
}