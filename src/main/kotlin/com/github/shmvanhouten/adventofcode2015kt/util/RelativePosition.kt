package com.github.shmvanhouten.adventofcode2015kt.util

enum class RelativePosition(val coordinate: Coordinate) {
    TOP(Coordinate(0, -1)),
    TOP_RIGHT(Coordinate(1, -1)),
    RIGHT(Coordinate(1, 0)),
    BOTTOM_RIGHT(Coordinate(1, 1)),
    BOTTOM(Coordinate(0, 1)),
    BOTTOM_LEFT(Coordinate(-1, 1)),
    LEFT(Coordinate(-1, 0)),
    TOP_LEFT(Coordinate(-1, -1))
}