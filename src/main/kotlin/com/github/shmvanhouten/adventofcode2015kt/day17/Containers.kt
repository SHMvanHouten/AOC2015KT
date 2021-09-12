package com.github.shmvanhouten.adventofcode2015kt.day17

import com.github.shmvanhouten.adventofcode2015kt.util.allWithMinOf

private const val GOAL = 150

fun main() {
    val containers = setOf(
        Container(1, 50),
        Container(2, 44),
        Container(3, 11),
        Container(4, 49),
        Container(5, 42),
        Container(6, 46),
        Container(7, 18),
        Container(8, 32),
        Container(9, 26),
        Container(10, 40),
        Container(11, 21),
        Container(12, 7),
        Container(13, 18),
        Container(14, 43),
        Container(15, 10),
        Container(16, 47),
        Container(17, 36),
        Container(18, 24),
        Container(19, 22),
        Container(20, 40)
    )

    val allContainerCombos = findContainerCombos(containers)
    println(allContainerCombos.size)
    // 654

    println(allContainerCombos.allWithMinOf { it.size }.size)
    // 57
}

fun findContainerCombos(
    nodes: Set<Container>,
    routes: Set<Set<Container>> = emptySet(),
    currentContainerCombo: Set<Container> = emptySet(),
    sum: Int = 0
): Set<Set<Container>> {
    return when {
        sum == GOAL -> routes.plusElement(currentContainerCombo)
        sum > GOAL -> routes
        else -> nodes.flatMap { container ->
            findContainerCombos(
                nodes.minus(container),
                routes,
                currentContainerCombo.plus(container),
                sum + container.value
            )
        }.toSet()
    }
}

data class Container(val id: Int, val value: Int) {
    override fun equals(other: Any?): Boolean {
        return if (other is Container) {
            this.id == other.id
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return id
    }
}

