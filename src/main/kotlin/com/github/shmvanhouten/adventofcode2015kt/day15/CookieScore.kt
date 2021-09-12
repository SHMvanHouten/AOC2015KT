package com.github.shmvanhouten.adventofcode2015kt.day15


fun getBest500CalorieCookieScore(vararg ingredients: Ingredient): Long {
    return getBest500CalorieCookieScore(ingredients.asList())
}

private fun getBest500CalorieCookieScore(ingredients: List<Ingredient>): Long {
    return getAllPositiveCookies(ingredients)
        .filter { it.totalCalories == 500L }
        .maxOf { it.score }
}

fun getBestCookieScore(vararg ingredient: Ingredient): Long {
    return getBestCookieScore(ingredient.asList())
}

fun getBestCookieScore(ingredients: List<Ingredient>): Long {

    return getAllPositiveCookies(ingredients)
        .maxOf { it.score }
}

private fun getAllPositiveCookies(ingredients: List<Ingredient>) =
    getAllPossibleIngredientAmountVariations(ingredients.size)
        .asSequence()
        .map { toCookie(assignAmounts(it, ingredients)) }
        .filter { hasNoNegativeProperties(it) }

fun assignAmounts(amounts: List<Int>, ingredients: List<Ingredient>): List<Ingredient> {
    return amounts.zip(ingredients)
        .map { (amount, ingredient) -> ingredient * amount }
}

fun hasNoNegativeProperties(cookie: Cookie): Boolean {
    return cookie.totalCapacity > 0 &&
            cookie.totalDurability > 0 &&
            cookie.totalFlavor > 0 &&
            cookie.totalTexture > 0
}

fun toCookie(ingredients: List<Ingredient>): Cookie {
    return Cookie(
        ingredients.sumOf { it.capacity },
        ingredients.sumOf { it.durability },
        ingredients.sumOf { it.flavor },
        ingredients.sumOf { it.texture },
        ingredients.sumOf { it.calories }
    )
}

fun getAllPossibleIngredientAmountVariations(
    amountOfIngredients: Int,
    teaspoonsLeft: Int = 100,
    currentMix: List<Int> = emptyList(),
    mixes: List<List<Int>> = emptyList()
): List<List<Int>> {
    return if (amountOfIngredients == 1) {
        val finishedMix = currentMix.plus(listOf(teaspoonsLeft))
        mixes.plus(listOf(finishedMix))
    } else {
        (0..teaspoonsLeft).flatMap { teaspoons ->
            getAllPossibleIngredientAmountVariations(
                amountOfIngredients - 1,
                teaspoonsLeft - teaspoons,
                currentMix.plus(listOf(teaspoons)),
                mixes
            )
        }
    }
}
