package com.github.shmvanhouten.adventofcode2015kt.day18

import com.github.shmvanhouten.adventofcode2015kt.util.Coordinate

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

    fun tick(): Field = this.copy(lights = tickLights())

    private fun tickLights() =
        this.lights.values.map { tick(it) }
            .associateBy { it.coordinate }

    private fun tick(light: Light): Light {
        return Light(shouldBeOn(light), light.coordinate)
    }

    private fun shouldBeOn(light: Light): Boolean {
        val adjacentLights = countAdjacentLightsThatAreOn(light)
        return light.isOn && (adjacentLights == 2 || adjacentLights == 3)
                || !light.isOn && adjacentLights == 3
    }

    private fun countAdjacentLightsThatAreOn(
        light: Light,
    ) = light.coordinate.getNeighbours()
        .mapNotNull { lights[it] }
        .count { it.isOn }

}

data class Dimension(val width: Int, val height: Int)
