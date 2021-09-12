package com.github.shmvanhouten.adventofcode2015kt.day15

data class Ingredient(
    val capacity: Long,
    val durability: Long,
    val flavor: Long,
    val texture: Long,
    val calories: Long
) {
    operator fun times(multiplicand: Int): Ingredient {
        return Ingredient(
            capacity * multiplicand,
            durability * multiplicand,
            flavor * multiplicand,
            texture * multiplicand,
            calories * multiplicand
        )
    }
}