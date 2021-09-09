package com.github.shmvanhouten.adventofcode2015kt.day24

import kotlin.math.abs

fun packageDistributions(packages: String): List<Distribution> {
    return packages.lines()
        .map { it.toInt() }
        .let { permuteDistributionsTo3Gourps(it) }
}

fun permuteDistributionsTo3Gourps(packages: List<Int>): List<Distribution> {
    return packages
        .permuteToTwoWeightedLists(list1Weight = 2)
        .permuteToThreeWeightedLists()
        .map { toDistribution(it) }
}

fun toDistribution(lists: Triple<List<Int>, List<Int>, List<Int>>): Distribution {
    return Distribution(lists.first, lists.second, lists.third)
}

private fun List<Pair<List<Int>, List<Int>>>.permuteToThreeWeightedLists(): List<Triple<List<Int>, List<Int>, List<Int>>> {
    return this.flatMap { (firstList, secondList) -> secondList.permuteToTwoWeightedLists().map { Triple(firstList, it.first, it.second) } }
}

private fun List<Int>.permuteToTwoWeightedLists(
    list1: List<Int> = emptyList(),
    list2: List<Int> = emptyList(),
    list1Weight: Int = 1
): List<Pair<List<Int>, List<Int>>> {
    when {
//        abs(list1.sum() * list1Weight - list2.sum()) > this.sum() * list1Weight -> {
//            return emptyList()
//        }
        this.isEmpty() -> {
            return if(list1.sum() * list1Weight == list2.sum()) {
                listOf(list1 to list2)
            } else {
                emptyList()
            }
        }
        else -> {
            return this.map { element: Int ->
                val rest = this - element
                return rest.permuteToTwoWeightedLists(list1 + element, list2, list1Weight) + rest.permuteToTwoWeightedLists(list1, list2 + element, list1Weight)
            }.flatten()

        }
    }
}

private fun <E> List<E>.subList(fromIndex: Int): List<E> {
    return this.subList(fromIndex, this.size)
}

data class Distribution(
    val group1: List<Int>,
    val group2: List<Int>,
    val group3: List<Int>
)
