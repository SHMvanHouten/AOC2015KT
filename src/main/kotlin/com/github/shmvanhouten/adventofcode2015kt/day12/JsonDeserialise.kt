package com.github.shmvanhouten.adventofcode2015kt.day12

fun toJsonArray(jsonString: String): JsonArray {
    for (c in jsonString) {
        if(c == '[') {

        }
    }
    return JsonArray(emptyList())
}

data class JsonObject (val objects: List<JsonObject>, val arrays: List<JsonArray>)

data class JsonArray(val elements: List<Any>)

