package com.github.shmvanhouten.adventofcode2015kt.util

fun <T> permute(nodes: Collection<T>, maxSize: Int = nodes.size) =
    permute(nodes, maxSize, emptyList(), emptyList())

private fun <T> permute(
    nodes: Collection<T>,
    maxSize: Int,
    routes: List<List<T>>,
    currentRoute: List<T>
): List<List<T>> {
    return if (currentRoute.size == maxSize) {
        routes.plus(listOf(currentRoute))
    } else {
        nodes.flatMap { node -> permute(nodes.minus(node), maxSize, routes, currentRoute.plus(node)) }
    }
}