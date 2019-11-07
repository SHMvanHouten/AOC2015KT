package com.github.shmvanhouten.adventofcode2015kt.day18

import com.github.shmvanhouten.adventofcode2015kt.util.Coordinate
import com.github.shmvanhouten.adventofcode2015kt.util.FileReader.readFile
import java.awt.Dimension


fun getFile(path: String): String {
    return readFile(path)
}

fun parseInput(input: String): Field {
    val rawLines = input.split('\n')
    val fieldMap = rawLines
        .mapIndexed { y, line -> toLineOfLights(line, y) }
        .flatten()
        .map { it.coordinate to it }
        .toMap()
    return Field(fieldMap, Dimension(rawLines.first().length, rawLines.size))
}

fun tick(field: Field, times: Int): Field {
    var tickedField = field
    for (i in 0.until(times)) {
        tickedField = Field(tickLights(tickedField), tickedField.dimensions)
    }
    return tickedField
}

fun tickWithLightsStuck(field: Field, times: Int): Field {
    var tickedField = field.withCornersStuck()
    for (i in 0.until(times)) {
        tickedField = Field(tickLights(tickedField), tickedField.dimensions).withCornersStuck()
    }
    return tickedField
}


fun tick(field: Field): Field {
    return Field(tickLights(field), field.dimensions)
}

private fun tickLights(field: Field) =
    field.lights.values.map { tick(it, field) }
        .map { it.coordinate to it }
        .toMap()

fun tick(light: Light, field: Field): Light {
    return Light(shouldBeOn(light, field.lights), light.coordinate)
}

fun shouldBeOn(light: Light, field: Map<Coordinate, Light>): Boolean {
    val adjacentLights = light.coordinate.getNeighbours()
        .map { field[it] }
        .map { it?.isOn ?: false }
        .count{ it }
    return light.isOn && (adjacentLights == 2 || adjacentLights == 3)
            || !light.isOn && adjacentLights == 3
}

fun toLineOfLights(line: String, y: Int): List<Light> {
    return line.mapIndexed { x, c -> toLight(c, x, y) }
}

fun toLight(c: Char, x: Int, y: Int): Light {
    return Light(c == '#', Coordinate(x, y))
}

data class Light(val isOn: Boolean, val coordinate: Coordinate)