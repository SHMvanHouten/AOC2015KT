package com.github.shmvanhouten.adventofcode2015kt.day13

data class Guest(val name: String, val feelingsTowardOthers: Map<String, Happiness>) {
    fun feelingsTowards(guest: Guest): Happiness {
        return feelingsTowardOthers[guest.name] ?: error("could not find feelings of ${this.name} towards guest ${guest.name}")
    }
}