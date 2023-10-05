package com.example.gametaskforheadsandhands.domain.usecases

import com.example.gametaskforheadsandhands.domain.repositories.GameRepository

class HitUseCase(private val repository: GameRepository) {
    operator fun invoke(
        attack: Int,
        enemyDefence: Int,
        minDamage: Int,
        maxDamage: Int,
    ): Int {
        return repository.hit(
            attack,
            enemyDefence,
            minDamage,
            maxDamage
        )
    }
}