package com.example.gametaskforheadsandhands.data

import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.Level
import com.example.gametaskforheadsandhands.domain.entities.monsters.Dragon
import com.example.gametaskforheadsandhands.domain.entities.monsters.Goblin
import com.example.gametaskforheadsandhands.domain.entities.monsters.PoisonFlower
import com.example.gametaskforheadsandhands.domain.entities.monsters.WaterMonster
import com.example.gametaskforheadsandhands.domain.repositories.GameRepository
import java.util.LinkedList

object GameRepositoryImpl : GameRepository {

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
        listMonsters.add(EntitiesObject.goblin)
        listMonsters.add(EntitiesObject.poisonFlower)
        listMonsters.add(EntitiesObject.waterMonster)
        listMonsters.add(EntitiesObject.dragon)
        return listMonsters
    }

    override fun launchValuesMonsters(level: Level) {
        val increasingValues = when (level) {
            Level.EASY -> 4
            Level.NORMAL -> 6
            Level.HARD -> 8
        }
        with(EntitiesObject.goblin) {
            attack = 2
            defence = 4
            maxHealth = 4
            currentHealth = maxHealth
            minDamage = 1
            maxDamage = 3
        }
        val listMonsters = arrayListOf<Entity>()
        listMonsters.add(EntitiesObject.poisonFlower)
        listMonsters.add(EntitiesObject.waterMonster)
        listMonsters.add(EntitiesObject.dragon)

        for (i in 0 until listMonsters.size) {
            with(listMonsters[i]) {
                attack = EntitiesObject.goblin.attack + increasingValues * (i + 1)
                defence = EntitiesObject.goblin.defence + increasingValues * (i + 1)
                maxHealth = EntitiesObject.goblin.maxHealth + increasingValues * (i + 1)
                currentHealth = EntitiesObject.goblin.currentHealth + increasingValues * (i + 1)
                maxDamage = EntitiesObject.goblin.maxDamage + increasingValues * (i + 1)
                minDamage = EntitiesObject.goblin.minDamage + increasingValues * (i + 1)
            }
        }
    }
}