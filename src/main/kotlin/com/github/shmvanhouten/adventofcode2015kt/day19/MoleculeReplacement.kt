package com.github.shmvanhouten.adventofcode2015kt.day19

fun listReplacements(input: String, dictionary: Dictionary): List<String> {
    val inputMolecule = input.toMolecule()
    return listReplacements(inputMolecule, dictionary)
}

fun listReplacements(
    inputMolecule: Molecule,
    dictionary: Dictionary
): List<String> {
    return inputMolecule.mapIndexed { i, atom ->
        dictionary[atom]
            ?.map { replacement -> insertAtIndex(inputMolecule, i, replacement) }
            ?: emptyList()
    }.flatten()
        .distinct()
        .map { it.joinToString("") }
}

private fun insertAtIndex(
    input: Molecule,
    i: Int,
    replacement: Molecule
): Molecule {
    val (before, after) = splitByIndexExcluding(input, i)
    return before + replacement + after
}

private fun splitByIndexExcluding(
    input: Molecule,
    i: Int
): Pair<Molecule, Molecule> {
    return input.subList(0, i) to moleculeAfterIndexOrEmpty(input, i + 1)
}

private fun moleculeAfterIndexOrEmpty(
    input: Molecule,
    index: Int
): List<String> {
    return if (input.lastIndex >= index) {
        input.subList(index, input.size)
    } else {
        emptyList()
    }
}

fun String.toMolecule(): Molecule {
    val mutableMolecule = mutableListOf<Atom>()
    var i = 0
    while (i < this.length) {
        when {
            i == this.lastIndex -> mutableMolecule.add(this[i].toString())
            this[i + 1].isLowerCase() -> {
                mutableMolecule.add("${this[i]}${this[++i]}")
            }
            else -> mutableMolecule.add(this[i].toString())
        }
        i++
    }
    return mutableMolecule
}

typealias Dictionary = Map<Atom, List<Molecule>>

typealias Atom = String
typealias Molecule = List<String>
