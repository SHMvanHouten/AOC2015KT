package com.github.shmvanhouten.adventofcode2015kt.day17

private const val GOAL = 150

fun main() {
    val containers = listOf(
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

    val allContainerCombos = getAllContainerCombos(containers)
    println(allContainerCombos.size)
    // 654

    val minsize = allContainerCombos
        .map { it.size }
        .min()?: error("tried to min an empty list, stupid")

    println(allContainerCombos.filter { it.size == minsize }.size)
    // 57
}

fun getAllContainerCombos(nodes: List<Container>) = findContainerCombos(nodes, emptySet(), emptyList(), 0)

fun findContainerCombos(
    nodes: List<Container>,
    routes: Set<List<Container>>,
    currentContainerCombo: List<Container>,
    sum: Int
): Set<List<Container>> {
    return when {
        sum == GOAL -> routes.plus(setOf(currentContainerCombo.sortedBy { it.id }))
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

