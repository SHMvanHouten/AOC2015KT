package com.github.shmvanhouten.adventofcode2015kt.day18

import com.github.shmvanhouten.adventofcode2015kt.util.Coordinate
import java.awt.Dimension

data class Field(val lights: Map<Coordinate, Light>, val dimensions: Dimension) {

    override fun toString(): String {
        return 0.until(dimensions.height)
            .flatMap { y ->
                0.until(dimensions.width).map { x -> drawLight(findLightOnCoordinate(x, y)) }.plus(listOf('\n'))
            }
            .joinToString("").trim()
    }

    private fun findLightOnCoordinate(
        x: Int,
        y: Int
    ) = lights[Coordinate(x, y)] ?: error("No light on coordinate $x, $y")

    private fun drawLight(light: Light): Char {
        return if (light.isOn) {
            '#'
        } else {
            '.'
        }
    }

    fun countLights(): Int {
        return lights.values.map { it.isOn }.count { it }
    }

    fun withCornersStuck(): Field {
        val mutableLights = lights.toMutableMap()
        mutableLights[Coordinate(0,0)] = Light(true, Coordinate(0, 0))
        mutableLights[Coordinate(dimensions.width - 1,0)] = Light(true, Coordinate(dimensions.width - 1, 0))
        mutableLights[Coordinate(0,dimensions.height - 1)] = Light(true, Coordinate(0, dimensions.height - 1))
        mutableLights[Coordinate(dimensions.width - 1, dimensions.height - 1)] = Light(true, Coordinate(dimensions.width - 1, dimensions.height - 1))
        return Field(mutableLights, dimensions)
    }

}