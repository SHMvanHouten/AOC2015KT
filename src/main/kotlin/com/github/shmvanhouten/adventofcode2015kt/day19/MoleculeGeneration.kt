package com.github.shmvanhouten.adventofcode2015kt.day19

fun countStepsToTargetMolecule(targetMolecule: String, dictionary: List<Pair<String, String>>): Int {
    val eReplacements = dictionary.filter { it.first == "e" }.map { it.second }.toSet()
    val otherReplacements = dictionary.filter { it.first != "e" }
    var steps = 0
    var resultMolecule = targetMolecule
    while(resultMolecule != "e") {
        if(eReplacements.contains(resultMolecule)) {
            resultMolecule = "e"
        } else {
            val (replacement, applicablePortion) = otherReplacements
                .first { resultMolecule.contains(it.second) }
            resultMolecule = resultMolecule.replaceFirst(applicablePortion, replacement)
        }
        steps++
    }
    return steps
}
