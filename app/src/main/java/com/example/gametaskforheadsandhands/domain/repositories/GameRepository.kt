package com.example.gametaskforheadsandhands.domain.repositories

import com.example.gametaskforheadsandhands.domain.entities.Entity
import java.util.LinkedList

interface GameRepository {
    fun medicalKit (currentHealth: Int, maxHealth: Int): Int

    fun hit (
        attack: Int,
        enemyDefence: Int,
        minDamage: Int,
        maxDamage: Int,
        enemyHealth: Int
    ): Int

    fun createOrderMonsters(): LinkedList<Entity>
}