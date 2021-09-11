package com.github.shmvanhouten.adventofcode2015kt.day9

import com.github.shmvanhouten.adventofcode2015kt.util.getAllRoutes

fun parseInput(input: String): List<Edge> {
    return input.split('\n')
        .map { toPair(it) }
}

fun findShortestDistance(vararg edges: Edge): Int? {
    return findShortestDistance(edges.toList())
}

fun findShortestDistance(edges: List<Edge>): Int? {
    return listDistancesForAllRoutes(edges).minOrNull()
}

fun findLongestDistance(edges: List<Edge>): Int? {
    return listDistancesForAllRoutes(edges).maxOrNull()
}

private fun listDistancesForAllRoutes(edges: List<Edge>): List<Int> {
    val nodes = listDistinctNodes(edges)
    val distanceMap = mapDistances(nodes, edges)
    return getAllRoutes(nodes)
        .map { route -> calculateTotalDistance(route, distanceMap) }
}

private fun calculateTotalDistance(route: List<Node>, distanceMap: Map<Node, Map<Node, Distance>>): Distance {
    return route
        .windowed(2)
        .sumOf { (origin, destination) -> distanceMap.distanceFrom(origin).toDestination(destination) }
}

private fun mapDistances(nodes: List<String>, edges: List<Edge>): Map<Node, Map<Node, Distance>> {
    return nodes.associateWith { origin ->
        listDistancesFromCurrentNodeToDestinations(origin, nodes.minus(origin), edges)
    }
}

private fun listDistancesFromCurrentNodeToDestinations(
    origin: Node,
    destinations: List<Node>,
    edges: List<Edge>
): Map<Node, Distance> {
    return destinations.associateWith { destination ->
        edges.findDistance(origin, destination)
    }
}

private fun List<Edge>.findDistance(origin: Node, destination: Node): Distance {
    return this.first {
        it.connects(origin, destination)
    }.distance
}

private fun listDistinctNodes(edges: List<Edge>): List<String> {
    return edges.map { it.origin }
        .plus(edges.map { it.destination })
        .distinct()
}

private fun toPair(raw: String): Edge {
    val split = raw.split(' ')
    return Edge(split[0], split[2], split[4].toInt())
}

private fun Map<Node, Map<Node, Distance>>.distanceFrom(origin: Node): Map<Node, Distance> {
    return this[origin]!!
}

private fun Map<Node, Distance>.toDestination(destination: Node): Distance {
    return this[destination]!!
}

data class Edge(val origin: Node, val destination: Node, val distance: Distance) {
    fun connects(node1: Node, node2: Node): Boolean {
        return (node1 == destination && node2 == origin)
                || (node1 == origin && node2 == destination)
    }
}

typealias Node = String
typealias Distance = Int
