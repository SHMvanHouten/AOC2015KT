package com.github.shmvanhouten.adventofcode2015kt.util

fun diagonalSequenceFrom(startingCoordinate: Coordinate): Sequence<Coordinate> {
    return generateSequence(startingCoordinate) {
        if (it.y == startingCoordinate.y) it.copy(x = startingCoordinate.x, y = it.x + 1)

        else it.copy(x = it.x + 1, y = it.y - 1)
    }
}