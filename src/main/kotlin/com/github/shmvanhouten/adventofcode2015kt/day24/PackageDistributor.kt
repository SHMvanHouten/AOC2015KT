package com.github.shmvanhouten.adventofcode2015kt.day24

fun findBestFirstPackageGroup(packages: String): Group {
    return packages.lines()
        .map { it.toInt() }
        .let { findShortestFirstPackageGroups(it) }
        .sortedBy { group -> group.map { it.toLong() }.reduce(Long::times) }
        .first() // todo first that has can fill the other groups
}

fun findShortestFirstPackageGroups(packages: Group): List<Group> {
    if (packages.sum() % 3 != 0) error("packages can not be divided evenly")
    val groupSize = packages.sum() / 3
    val groups = packages
        .permuteGroupsOfWeight(groupSize)
    return groups.filter { it.size == groups.minOf { it.size } }
}


fun packageDistributions(packages: String): List<Distribution> {
    return packages.lines()
        .map { it.toInt() }
        .let { permuteDistributionsTo3Groups(it) }
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
