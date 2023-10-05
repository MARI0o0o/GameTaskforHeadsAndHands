package com.example.gametaskforheadsandhands.domain.usecases

import com.example.gametaskforheadsandhands.domain.entities.Level
import com.example.gametaskforheadsandhands.domain.repositories.GameRepository

class LaunchValuesMonstersUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level) {
        repository.launchValuesMonsters(level)
    }
}