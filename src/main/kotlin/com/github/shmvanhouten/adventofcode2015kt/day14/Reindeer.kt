package com.github.shmvanhouten.adventofcode2015kt.day14

class Reindeer(
    val name: String,
    val speed: Kmps,
    val flighttime: Seconds,
    val restTime: Seconds,
    var position: Int = 0,
    var points: Int = 0
) {

    var isResting = false
    var timeLeftForAction = flighttime
    fun runFor(seconds: Seconds): Int {

        for (second in 0..seconds) {
            run()
        }
        return position
    }

    fun run() {
        if (!isResting) {
            position += speed
        }
        timeLeftForAction--
        if (timeLeftForAction == 0) {
            isResting = !isResting
            timeLeftForAction = if (isResting) {
                restTime
            } else {
                flighttime
            }
        }
    }

    fun scorePoint() {
        points++
    }
}

typealias Kmps = Int
typealias Seconds = Int