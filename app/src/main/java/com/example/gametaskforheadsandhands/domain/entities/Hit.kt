package com.example.gametaskforheadsandhands.domain.entities

interface Hit {
    operator fun invoke (
        attack: Int,
        enemyDefence: Int,
        minDamage: Int,
        maxDamage: Int,
        enemyHealth: Int
    ): Int {
        var attackModifier = attack - (enemyDefence + 1)
        if (attackModifier < 1) {
            attackModifier = 1
        }
        var hitSuccess = false
        for (i in 1..attackModifier) {
            val diceRoll = (MIN_CUBE_RANGE..MAX_CUBE_RANGE).random()
            if (diceRoll in MIN_SUCCESS_DICE_ROLL..MAX_SUCCESS_DICE_ROLL) {
                hitSuccess = true
                break
            }
        }
        if (hitSuccess) {
            return (minDamage..maxDamage).random()
        }
        return 0
    }

    companion object {
        private const val MIN_CUBE_RANGE = 1
        private const val MAX_CUBE_RANGE = 6
        private const val MIN_SUCCESS_DICE_ROLL = 5
        private const val MAX_SUCCESS_DICE_ROLL = 6
    }
}