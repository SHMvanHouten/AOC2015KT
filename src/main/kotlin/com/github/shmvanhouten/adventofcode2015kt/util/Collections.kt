package com.github.shmvanhouten.adventofcode2015kt.util

fun <ELEMENT, VALUE_TO_COMPARE : Comparable<VALUE_TO_COMPARE>> Collection<ELEMENT>.allWithMaxOf(selector: (ELEMENT) -> VALUE_TO_COMPARE): List<ELEMENT> {
    val iterator = iterator()
    if (!iterator.hasNext()) return emptyList()
    val maxElements = mutableListOf(iterator.next())
    if (!iterator.hasNext()) return maxElements
    var maxValue = selector(maxElements.first())
    do {
        val e = iterator.next()
        val v = selector(e)
        if (v > maxValue) {
            maxElements.clear()
            maxElements.add(e)
            maxValue = v
        } else if (v == maxValue) {
            maxElements.add(e)
        }
    } while (iterator.hasNext())
    return maxElements
}

fun <ELEMENT, VALUE_TO_COMPARE : Comparable<VALUE_TO_COMPARE>> Collection<ELEMENT>.allWithMinOf(selector: (ELEMENT) -> VALUE_TO_COMPARE): List<ELEMENT> {
    val iterator = iterator()
    if (!iterator.hasNext()) return emptyList()
    val minElements = mutableListOf(iterator.next())
    if (!iterator.hasNext()) return minElements
    var minValue = selector(minElements.first())
    do {
        val e = iterator.next()
        val v = selector(e)
        if (v < minValue) {
            minElements.clear()
            minElements.add(e)
            minValue = v
        } else if (v == minValue) {
            minElements.add(e)
        }
    } while (iterator.hasNext())
    return minElements
}