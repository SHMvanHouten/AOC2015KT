package com.github.shmvanhouten.adventofcode2015kt.day21

class Shop {
    val weapons = listOf(
        Weapon(4, 8),  // Dagger
        Weapon(5, 10), // Shortsword
        Weapon(6, 25), // Warhammer
        Weapon(7, 40), // Longsword
        Weapon(8, 74)  // Greataxe
    )

    val armors = listOf(
        Armor(1, 13), //Leather
        Armor(2, 31), //Chainmail
        Armor(3, 53), //Splintmail
        Armor(4, 75), //Bandedmail
        Armor(5, 102) //Platemail
    )

    val rings = listOf(
       Ring(1, 0, 25), //Damage +1
       Ring(2, 0, 50), //Damage +2
       Ring(3, 0, 100), //Damage +3
       Ring(0, 1, 20), //Defense +1
       Ring(0, 2, 40), //Defense +2
       Ring(0, 3, 80) //Defense +3
    )
}