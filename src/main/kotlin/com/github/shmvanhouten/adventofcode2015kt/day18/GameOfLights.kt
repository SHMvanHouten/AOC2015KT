package com.github.shmvanhouten.adventofcode2015kt.day18

import com.github.shmvanhouten.adventofcode2015kt.util.Coordinate

fun tick(field: Field, times: Int): Field {
    return 0.until(times)
        .fold(field) {tickedField, _ ->
            tickedField.tick()
        }
}

fun tickWithLightsStuck(field: Field, times: Int): Field {
    return 0.until(times)
        .fold(field.withCornersStuck()) {tickedField, _ ->
            tickedField.tick().withCornersStuck()
        }
}

fun parseInput(input: String): Field {
    val rawLines = input.split('\n')
    val fieldMap = rawLines
        .mapIndexed { y, line -> toLineOfLights(line, y) }
        .flatten().associateBy { it.coordinate }
    return Field(fieldMap, Dimension(rawLines.first().length, rawLines.size))
}

private fun toLineOfLights(line: String, y: Int): List<Light> {
    return line.mapIndexed { x, c -> toLight(c, x, y) }
}

private fun toLight(c: Char, x: Int, y: Int): Light {
    return Light(c == '#', Coordinate(x, y))
}

data class Light(val isOn: Boolean, val coordinate: Coordinate)