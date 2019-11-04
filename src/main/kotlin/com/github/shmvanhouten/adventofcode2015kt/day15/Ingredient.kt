package com.github.shmvanhouten.adventofcode2015kt.day15

data class Ingredient(
    val capacity: Long,
    val durability: Long,
    val flavor: Long,
    val texture: Long,
    val calories: Long
) {
    operator fun times(multiplicator: Int): Ingredient {
        return Ingredient(
            capacity * multiplicator,
            durability * multiplicator,
            flavor * multiplicator,
            texture * multiplicator,
            calories * multiplicator
        )
    }
}