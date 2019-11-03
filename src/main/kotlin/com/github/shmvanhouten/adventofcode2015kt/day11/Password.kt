package com.github.shmvanhouten.adventofcode2015kt.day11

fun Password.next(): Password {
    var overflowed = true
    var reversedResult = StringBuilder()
    for (c in reversed()) {
        if(overflowed) {
            reversedResult = reversedResult.append(c.next())
            overflowed = c.next() == 'a'
        } else {
            reversedResult.append(c)
        }

    }

    return reversedResult.toString().reversed()
}
typealias Password = String

fun LowerCase.next() : LowerCase {
    return if(this == 'z') {
        'a'
    } else {
        (this.toInt() + 1).toChar()
    }
}

typealias LowerCase = Char