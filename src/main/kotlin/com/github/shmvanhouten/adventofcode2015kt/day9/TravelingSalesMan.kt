package com.github.shmvanhouten.adventofcode2015kt.day9

import java.lang.RuntimeException

fun findShortestDistance(vararg pairDistances: PairDistance): Int? {
    return findShortestDistance(pairDistances.toList())
}

fun findShortestDistance(pairDistances: List<PairDistance>): Int? {
    return listAllRouteDistances(pairDistances).min()
}

fun findLongestDistance(pairDistances: List<PairDistance>): Int? {
    return listAllRouteDistances(pairDistances).max()
}

private fun listAllRouteDistances(pairDistances: List<PairDistance>): List<Int> {
    val nodes = getDistinctNodes(pairDistances)
    val routes = getAllRoutes(nodes)
    val distanceMap = mapDistances(nodes, pairDistances)
    return routes.map { getTotalDistance(it, distanceMap) }
}

fun getTotalDistance(route: List<String>, distanceMap: Map<String, Map<String, Int>>): Int {
    return route
        .dropLast(1)
        .mapIndexed { i, node -> distanceMap[node]!![route[i + 1]]!! }
        .sum()
}

fun mapDistances(nodes: List<String>, pairDistances: List<PairDistance>): Map<String, Map<String, Int>> {
    return nodes.map { origin ->
        origin to mapDistancesFromCurrentNodeToNodes(origin, nodes.minus(origin), pairDistances)
    }.toMap()
}

private fun mapDistancesFromCurrentNodeToNodes(
    origin: String,
    otherNodes: List<String>,
    pairDistances: List<PairDistance>
): Map<String, Int> {
    return otherNodes.map { destination ->
        destination to getDistance(origin, destination, pairDistances)
    }.toMap()
}

fun getDistance(origin: String, destination: String, pairDistances: List<PairDistance>): Int {
    return pairDistances.find {
        (it.destination == destination && it.origin == origin)
                || (it.destination == origin && it.origin == destination)
    }?.distance ?: throw RuntimeException("No distance found for $origin and $destination")
}

private fun getAllRoutes(nodes: List<String>) = findRoutes(nodes, emptyList(), emptyList())

fun findRoutes(
    nodes: List<String>,
    routes: List<List<String>>,
    currentRoute: List<String>
): List<List<String>> {
    return if (atEndOfRoute(nodes)) {
        routes.plus(listOf(currentRoute))
    } else {
        nodes.flatMap { node -> findRoutes(nodes.minus(node), routes, currentRoute.plus(node)) }
    }
}

private fun atEndOfRoute(nodes: List<String>) = nodes.isEmpty()

fun getDistinctNodes(pairDistances: List<PairDistance>): List<String> {
    return pairDistances.map { it.origin }
        .plus(pairDistances.map { it.destination })
        .distinct()
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
