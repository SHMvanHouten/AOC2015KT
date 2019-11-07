package com.github.shmvanhouten.adventofcode2015kt.util

import com.github.shmvanhouten.adventofcode2015kt.util.RelativePosition.*


data class Coordinate(val x: Int, val y: Int) {
    fun getNeighbours(): Set<Coordinate> {
        return setOf(
            this.relativeTo(TOP.coordinate),
            this.relativeTo(TOP_RIGHT.coordinate),
            this.relativeTo(RIGHT.coordinate),
            this.relativeTo(BOTTOM_RIGHT.coordinate),
            this.relativeTo(BOTTOM.coordinate),
            this.relativeTo(BOTTOM_LEFT.coordinate),
            this.relativeTo(LEFT.coordinate),
            this.relativeTo(TOP_LEFT.coordinate)
        )
    }

}

fun Coordinate.relativeTo(coordinate: Coordinate): Coordinate {
    val x = this.x + coordinate.x
    val y = this.y + coordinate.y
    return Coordinate(x, y)
}

