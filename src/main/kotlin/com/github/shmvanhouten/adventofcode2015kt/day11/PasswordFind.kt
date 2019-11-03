package com.github.shmvanhouten.adventofcode2015kt.day11

fun findNext(password: Password): Password {
    var possibleNextPassword = password.next()
    while (!isValid(possibleNextPassword)) {
        possibleNextPassword = possibleNextPassword.next()
    }
    return possibleNextPassword
}