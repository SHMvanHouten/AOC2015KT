package com.github.shmvanhouten.adventofcode2015kt.day24

import java.util.*

fun findBestFirstPackageGroup(packages: String, amountOfGroups: Int): Group {
    return packages.lines()
        .map { it.toInt() }
        .let { findShortestFirstPackageGroups(it, amountOfGroups) }
        .sortedBy { group -> group.map { it.toLong() }.reduce(Long::times) }
        .first() // todo first that has can fill the other groups
}

private fun findShortestFirstPackageGroups(packages: Group, amountOfGroups: Int): List<Group> {
    if (packages.sum() % amountOfGroups != 0) error("packages can not be divided evenly")
    val requiredGroupWeight = packages.sum() / amountOfGroups
    return listShortestGroups(packages, requiredGroupWeight)
}

private fun listShortestGroups(packages: List<Int>, groupWeight: Int): List<Group> {
    val potentialGroups: Queue<Pair<Group, List<Int>>> = LinkedList(packages
        .mapIndexed { index, pakje ->
            listOf(pakje) to packages.subList(index + 1, packages.size)
        }.toMutableList()
    )
    val groups = mutableListOf<Group>()
    var minGroupSize = Int.MAX_VALUE
    while (potentialGroups.isNotEmpty()) {
        val (groupSoFar, remainingPackages) = potentialGroups.poll()!!
        if (groupSoFar.size <= minGroupSize && groupSoFar.sum() == groupWeight) {
            if (groupSoFar.size < minGroupSize) {
                groups.clear()
                minGroupSize = groupSoFar.size
            }
            groups.add(groupSoFar)
        } else if (groupSoFar.size >= minGroupSize) {
            // drop it
        } else {
            potentialGroups.addAll(remainingPackages
                .mapIndexed { index, pakje ->
                    groupSoFar + pakje to remainingPackages.subList(index + 1, remainingPackages.size)
                })
        }
    }
    return groups
}

typealias Group = List<Int>
