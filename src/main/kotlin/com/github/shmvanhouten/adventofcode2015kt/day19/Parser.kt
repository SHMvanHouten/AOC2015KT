package com.github.shmvanhouten.adventofcode2015kt.day19

import com.github.shmvanhouten.adventofcode2015kt.util.FileReader.readFile
import com.github.shmvanhouten.adventofcode2020.util.blocks

class Parser {

    fun toDictionaryAndInputMolecule(path: String): Pair<Dictionary, Molecule> {
        val blocks = readFile(path).blocks()
        return toDictionary(blocks[0].lines()) to blocks[1].toMolecule()
    }

    fun toDictionary(rawEntries: List<String>): Dictionary {
        return rawEntries.map { it.split(" => ") }
            .map { (k, v) -> k to v.toMolecule() }
            .groupBy({it.first}, {it.second})
    }
}