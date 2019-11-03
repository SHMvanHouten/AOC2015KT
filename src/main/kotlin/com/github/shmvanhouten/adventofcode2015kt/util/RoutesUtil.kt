package com.github.shmvanhouten.adventofcode2015kt.util

fun <T> getAllRoutes(nodes: List<T>) = findRoutes(nodes, emptyList(), emptyList())

fun <T> findRoutes(
    nodes: List<T>,
    routes: List<List<T>>,
    currentRoute: List<T>
): List<List<T>> {
    return if (atEndOfRoute(nodes)) {
        routes.plus(listOf(currentRoute))
    } else {
        nodes.flatMap { node -> findRoutes(nodes.minus(node), routes, currentRoute.plus(node)) }
    }
}

private fun <T> atEndOfRoute(nodes: List<T>) = nodes.isEmpty()