package com.example.gametaskforheadsandhands.domain.usecases

import com.example.gametaskforheadsandhands.domain.repositories.GameRepository

class MedicalKitUseCase (private val repository: GameRepository){
    operator fun invoke (currentHealth: Int, maxHealth: Int): Int {
        return repository.medicalKit(currentHealth, maxHealth)
    }
}