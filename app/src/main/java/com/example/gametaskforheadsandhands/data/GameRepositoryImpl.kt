package com.example.gametaskforheadsandhands.data

import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.Hero
import com.example.gametaskforheadsandhands.domain.entities.Hit
import com.example.gametaskforheadsandhands.domain.entities.monsters.Dragon
import com.example.gametaskforheadsandhands.domain.entities.monsters.Goblin
import com.example.gametaskforheadsandhands.domain.entities.monsters.PoisonFlower
import com.example.gametaskforheadsandhands.domain.entities.monsters.WaterMonster
import com.example.gametaskforheadsandhands.domain.repositories.GameRepository
import java.util.LinkedList

object GameRepositoryImpl : GameRepository{

    private const val MIN_CUBE_RANGE = 1
    private const val MAX_CUBE_RANGE = 6
    private const val MIN_SUCCESS_DICE_ROLL = 5
    private const val MAX_SUCCESS_DICE_ROLL = 6
    override fun medicalKit(currentHealth: Int, maxHealth: Int): Int {
        val resultHealth = currentHealth + (maxHealth * 30 / 100)
        if (resultHealth > maxHealth) {
            return maxHealth
        }
        return resultHealth
    }

    override fun hit(
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

    override fun createOrderMonsters(): LinkedList<Entity> {
        var listMonsters = LinkedList<Entity>()
        listMonsters.add(Goblin())
        listMonsters.add(PoisonFlower())
        listMonsters.add(WaterMonster())
        listMonsters.add(Dragon())
        return listMonsters
    }
}