package com.github.shmvanhouten.adventofcode2015kt.day15

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

//Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
//Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
//A capacity of 44*-1 + 56*2 = 68
//A durability of 44*-2 + 56*3 = 80
//A flavor of 44*6 + 56*-2 = 152
//A texture of 44*3 + 56*-1 = 76


class CookieScoreTest {

    @Test
    internal fun `a single ingredient leads to a cookie score of each property (except calories) * 100, multiplied`() {
        val butterscotch = Ingredient(1, 2, 3, 4, 5)
        assertThat(getBestCookieScore(butterscotch), equalTo(2400000000L))

        val cinnamon = Ingredient(2, 2, 2, 2, 5)
        assertThat(getBestCookieScore(cinnamon), equalTo(1600000000L))
    }

    @Test
    internal fun `the test input should result in 62842880`() {
        val butterscotch = Ingredient(-1, -2, 6, 3, 8)
        val cinnamon = Ingredient(2, 3, -2, -1, 3)

        assertThat(getBestCookieScore(butterscotch, cinnamon), equalTo(62842880L))
    }

    @Test
    internal fun `part 1`() {
        val sprinkles = Ingredient(2, 0, -2, 0, 3)
        val butterscotch = Ingredient(0, 5, -3, 0, 3)
        val chocolate = Ingredient(0, 0, 5, -1, 8)
        val candy = Ingredient(0, -1, 0, 5, 8)

        assertThat(
            getBestCookieScore(sprinkles, butterscotch, chocolate, candy),
            equalTo(21367368)
        )
    }

    @Test
    internal fun `the test input for part 2 should result in 57600000`() {
        val butterscotch = Ingredient(-1, -2, 6, 3, 8)
        val cinnamon = Ingredient(2, 3, -2, -1, 3)

        assertThat(getBest500CalorieCookieScore(butterscotch, cinnamon), equalTo(57600000L))
    }


    @Test
    internal fun `part 2`() {
        val sprinkles = Ingredient(2, 0, -2, 0, 3)
        val butterscotch = Ingredient(0, 5, -3, 0, 3)
        val chocolate = Ingredient(0, 0, 5, -1, 8)
        val candy = Ingredient(0, -1, 0, 5, 8)

        assertThat(
            getBest500CalorieCookieScore(sprinkles, butterscotch, chocolate, candy),
            equalTo(1766400)
        )
    }

}