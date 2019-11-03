package com.github.shmvanhouten.adventofcode2015kt.day14

fun main() {
    println(
        parseReindeer(input)
        .map { it.runFor(2503) }
        .max()
    )
    // 2660

    println(calculatePoints(parseReindeer(input)))
    // 1138 < answer < 1918
}

fun calculatePoints(reindeer: List<Reindeer>): Int {
    for (i in (0..2503)) {
        println("")
        println("second $i")
        reindeer
            .forEach{
                it.run()
            }
        scoreReindeerInFirstPlace(reindeer)
    }
    return reindeer.map { it.points }.max() ?: error("empty list...")
}

private fun scoreReindeerInFirstPlace(reindeer: List<Reindeer>) {
    val topPosition = getTopPosition(reindeer)
    reindeer
        .filter { it.position == topPosition }
        .forEach{it.scorePoint()}
}

private fun getTopPosition(reindeer: List<Reindeer>) =
    reindeer.map { it.position }.max()
        ?: error("could not find top position for reindeer: ${reindeer.map { it.name to it.position }}")


fun parseReindeer(input: String): List<Reindeer> {
    return input
        .split('\n')
        .map { toReindeer(it) }
}

private fun toReindeer(raw: String) = toReindeer(raw.split(' '))

fun toReindeer(raw: List<String>): Reindeer {
    val name = raw[0]
    val speed = raw[3].toInt()
    val flightTime = raw[6].toInt()
    val restTime = raw[13].toInt()
    return Reindeer(name, speed, flightTime, restTime)
}

val input = """Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.
Rudolph can fly 3 km/s for 15 seconds, but then must rest for 28 seconds.
Donner can fly 19 km/s for 9 seconds, but then must rest for 164 seconds.
Blitzen can fly 19 km/s for 9 seconds, but then must rest for 158 seconds.
Comet can fly 13 km/s for 7 seconds, but then must rest for 82 seconds.
Cupid can fly 25 km/s for 6 seconds, but then must rest for 145 seconds.
Dasher can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
Dancer can fly 3 km/s for 16 seconds, but then must rest for 37 seconds.
Prancer can fly 25 km/s for 6 seconds, but then must rest for 143 seconds."""