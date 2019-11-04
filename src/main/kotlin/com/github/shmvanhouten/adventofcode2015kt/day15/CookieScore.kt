package com.github.shmvanhouten.adventofcode2015kt.day15


fun getBest500CaloryCookieScore(vararg ingredients: Ingredient): Long {
    return getBest500CaloryCookieScore(ingredients.asList())
}

private fun getBest500CaloryCookieScore(ingredients: List<Ingredient>): Long {
    val mixAmounts = getAllPossibleIngredientAmountVariations(ingredients.size)
    return mixAmounts
        .map { it to toCookie(assignAmounts(it, ingredients)) }
        .filter { hasNoNegativeProperties(it.second) }
        .filter { has500Calories(it.second) }
        .map { calculateScore(it.second) }
        .max() ?: error("empty list!")
}

fun has500Calories(cookie: Cookie): Boolean {
    return cookie.totalCalories == 500L
}

fun getBestCookieScore(vararg ingredient: Ingredient): Long {
    return getBestCookieScore(ingredient.asList())
}

fun getBestCookieScore(ingredients: List<Ingredient>): Long {

//    val allMixes: List<List<Ingredient>> = getAllPossibleIngredientMixes(ingredients)
//    return findBestCookieMix(allMixes)
    val mixAmounts = getAllPossibleIngredientAmountVariations(ingredients.size)
    return mixAmounts
        .map { it to toCookie(assignAmounts(it, ingredients)) }
        .filter { hasNoNegativeProperties(it.second) }
        .map { calculateScore(it.second) }
        .max() ?: error("empty list!")
}

fun assignAmounts(amounts: List<Int>, ingredients: List<Ingredient>): List<Ingredient> {
    return amounts.zip(ingredients)
        .map { (amount, ingredient) -> ingredient * amount }
}

fun calculateScore(cookie: Cookie): Long {
    return cookie.totalCapacity *
            cookie.totalDurability *
            cookie.totalFlavor *
            cookie.totalTexture
}

fun hasNoNegativeProperties(cookie: Cookie): Boolean {
    return cookie.totalCapacity > 0 &&
            cookie.totalDurability > 0 &&
            cookie.totalFlavor > 0 &&
            cookie.totalTexture > 0
}

fun toCookie(ingredients: List<Ingredient>): Cookie {
    return Cookie(
        ingredients.map { it.capacity }.sum(),
        ingredients.map { it.durability }.sum(),
        ingredients.map { it.flavor }.sum(),
        ingredients.map { it.texture }.sum(),
        ingredients.map { it.calories }.sum()
    )
}

fun getAllPossibleIngredientAmountVariations(amountOfIngredients: Int): List<List<Int>> {
    return getAllPossibleIngredientAmountVariations(
        amountOfIngredients,
        100,
        emptyList(),
        emptyList()
    )
}

fun getAllPossibleIngredientAmountVariations(
    amountOfIngredients: Int,
    teaspoonsLeft: Int,
    currentMix: List<Int>,
    mixes: List<List<Int>>
): List<List<Int>> {
    if (amountOfIngredients == 1) {
        val finishedMix = currentMix.plus(listOf(teaspoonsLeft))
        return mixes.plus(listOf(finishedMix))
    } else {
        return (0..teaspoonsLeft).flatMap { teaspoons ->
            getAllPossibleIngredientAmountVariations(
                amountOfIngredients - 1,
                teaspoonsLeft - teaspoons,
                currentMix.plus(listOf(teaspoons)),
                mixes
            )
        }
    }
}

private fun findBestCookieMix(allMixes: List<List<Ingredient>>): Long {
    return allMixes
        .map { toCookie(it) }
        .filter { hasNoNegativeProperties(it) }
        .sortedByDescending { calculateScore(it) }
        .map { calculateScore(it) }
        .max() ?: error("empty list!")
}

fun getAllPossibleIngredientMixes(ingredients: List<Ingredient>): List<List<Ingredient>> {
    return getAllPossibleIngredientMixes(ingredients, 100, emptyList(), emptyList())
}

fun getAllPossibleIngredientMixes(
    ingredients: List<Ingredient>,
    teaspoonsLeft: Int,
    currentMix: List<Ingredient>,
    mixes: List<List<Ingredient>>
): List<List<Ingredient>> {
    val ingredient = ingredients.first()
    val rest = ingredients.minus(ingredient)
    if (rest.isEmpty()) {
        val finishedMix = currentMix.plus(listOf(ingredient * teaspoonsLeft))
        return mixes.plus(listOf((finishedMix)))
    } else {
        return (0..teaspoonsLeft).flatMap { teaspoons ->
            getAllPossibleIngredientMixes(rest, (teaspoonsLeft - teaspoons), currentMix.plus(ingredient * teaspoons), mixes)
        }
    }
}
