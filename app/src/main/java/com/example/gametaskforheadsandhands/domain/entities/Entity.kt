package com.example.gametaskforheadsandhands.domain.entities

abstract class Entity(open var name: String) {

    var attack: Int = MIN_VALUE_CHARACTERISTICS
        set(value) {
            field = limitMaxValueCharacteristic(value, MAX_VALUE_CHARACTERISTICS)
        }
    var defence: Int = MIN_VALUE_CHARACTERISTICS
        set(value) {
            field = limitMaxValueCharacteristic(value, MAX_VALUE_CHARACTERISTICS)
        }
    var maxHealth: Int = MIN_VALUE_CHARACTERISTICS
        set(value) {
            field = limitNotMinus(value)
        }
    var currentHealth: Int = maxHealth
        set(value) {
            field = limitNotMinus(value)
        }
    var minDamage: Int = MIN_VALUE_CHARACTERISTICS
        set(value) {
            if (value <= maxDamage) {
                field = limitNotMinus(value)
            }
        }
    var maxDamage: Int = MIN_VALUE_CHARACTERISTICS
        set(value) {
            if (value >= minDamage) {
                field = limitNotMinus(value)
            }
        }

    companion object {
        private const val MIN_VALUE_CHARACTERISTICS = 1
        const val MAX_VALUE_CHARACTERISTICS = 30
    }
}



private fun limitNotMinus(value: Int): Int {
    if (value >= 0) {
        return value
    }
    return 0
}

private fun limitMaxValueCharacteristic(value: Int, maxValue: Int): Int {
    if (value in 1..maxValue) {
        return value
    }

    if (value > maxValue) {
        return maxValue
    }
    return 0
}
