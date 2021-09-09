package com.github.shmvanhouten.adventofcode2015kt.day24

import java.util.*

fun findBestFirstPackageGroup(packages: String, amountOfGroups: Int): Group {
    return packages.lines()
        .map { it.toInt() }
        .let { findShortestFirstPackageGroups(it, amountOfGroups) }
        .sortedBy { group -> group.map { it.toLong() }.reduce(Long::times) }
        .first() // todo first that has can fill the other groups
}

fun findShortestFirstPackageGroups(packages: Group, amountOfGroups: Int): List<Group> {
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

private fun List<Int>.permuteGroupsOfWeight(groupSize: Int, groupSoFar: List<Int> = emptyList()): List<List<Int>> {
    return if (groupSoFar.sum() == groupSize) {
        listOf(groupSoFar)
    } else if (groupSoFar.sum() > groupSize) {
        emptyList()
    } else {
        return this.mapIndexed { index, element: Int ->
            val rest = this.subList(index + 1, this.size)
            rest.permuteGroupsOfWeight(groupSize, groupSoFar + element)
        }.flatten()
    }
}

fun permuteDistributionsTo3Groups(packages: List<Int>): List<Distribution> {
    if (packages.sum() % 3 != 0) error("packages can not be divided evenly")
    val groupSize = packages.sum() / 3
    return packages
        .permuteGroupsOfWeight(groupSize)
        .map { it.sorted() }
        .distinct()
        .permute()
        .map { toDistribution(it) }
}

fun packageDistributions(packages: String): List<Distribution> {
    return packages.lines()
        .map { it.toInt() }
        .let { permuteDistributionsTo3Groups(it) }
}

private fun List<Group>.permute(groupsCombinationsSoFar: List<Group> = emptyList()): List<List<Group>> {
    if (this.isEmpty() && groupsCombinationsSoFar.size == 3) {
        return listOf(groupsCombinationsSoFar)
    } else if (this.isEmpty()) {
        return emptyList()
    } else {

        return this.map { group: Group ->
            val otherAvailableGroups: List<Group> = this
                .filter { otherGroup -> group.none { otherGroup.contains(it) } }

            otherAvailableGroups.permute(groupsCombinationsSoFar.plusElement(group))
        }.flatten()
    }
}

fun toDistribution(lists: List<List<Int>>): Distribution {
    return Distribution(lists[0], lists[1], lists[2])
}

data class Distribution(
    val group1: Group,
    val group2: Group,
    val group3: Group
)

typealias Group = List<Int>
