package com.github.shmvanhouten.adventofcode2015kt.util

object FileReader {

    fun readFile(relativePath: String): String{
        val stringBuilder = StringBuilder()

        val inputStream = this::class.java.getResourceAsStream(relativePath)
        inputStream.bufferedReader()
            .useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                    stringBuilder.append('\n')
                }
            }

        return stringBuilder.toString().trim()
    }
}