package com.github.shmvanhouten.adventofcode2015kt.day19

import com.github.shmvanhouten.adventofcode2015kt.util.FileReader.readFile

class Parser {

    fun toDictionaryAndInputMolecule(path: String): Pair<Dictionary, Molecule> {
        val rawDictionaryAndMolecule = readFile(path).split('\n')
            .filter { it.isNotBlank() }
        val dictionary: Dictionary = toDictionary(rawDictionaryAndMolecule.dropLast(1))
        val molecule = rawDictionaryAndMolecule.last().toMolecule()
        return dictionary to molecule
    }

    private fun toDictionary(rawEntries: List<String>): Dictionary {
        return rawEntries.map { it.split(" => ") }
            .map { (k, v) -> k to v.toMolecule() }
            .groupBy({it.first}, {it.second})
    }
}