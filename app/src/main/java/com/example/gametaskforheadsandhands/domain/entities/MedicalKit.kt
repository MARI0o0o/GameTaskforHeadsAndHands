package com.example.gametaskforheadsandhands.domain.entities

interface MedicalKit {

    operator fun invoke (currentHealth: Int, maxHealth: Int): Int {
        val resultHealth = currentHealth + (maxHealth * 30 / 100)
        if (resultHealth > maxHealth) {
            return maxHealth
        }
        return resultHealth
    }

}