package com.github.shmvanhouten.adventofcode2015kt.day25

import com.github.shmvanhouten.adventofcode2015kt.util.Coordinate
import com.github.shmvanhouten.adventofcode2015kt.util.diagonalSequenceFrom

private val topLeft = Coordinate(1, 1)

private const val STARTING_CODE = 20151125L
private const val MULTIPLICAND = 252533
private const val DIVISOR = 33554393

fun codeAt(coordinate: Coordinate): Long {
    return diagonalSequenceFrom(topLeft)
        .indexOf(coordinate)
        .let { 0.until(it).fold(STARTING_CODE) { previous, _ ->
            generateNextCode(previous)
        } }
}

fun generateNextCode(previous: Long): Long {
    return (previous * MULTIPLICAND) % DIVISOR
}

