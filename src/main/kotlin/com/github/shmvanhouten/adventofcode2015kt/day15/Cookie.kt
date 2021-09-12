package com.github.shmvanhouten.adventofcode2015kt.day15

data class Cookie(
    val totalCapacity: Long,
    val totalDurability: Long,
    val totalFlavor: Long,
    val totalTexture: Long,
    val totalCalories: Long
) {
    val score = totalCapacity *
            totalDurability *
            totalFlavor *
            totalTexture
}
