package com.github.shmvanhouten.adventofcode2015kt.util

fun <E> List<E>.splitIntoTwo(index: Int): Pair<List<E>, List<E>> {
    val firstPart = this.subList(0, index)
    val secondPart = this.subList(index, this.size)
    return Pair(firstPart, secondPart)
}