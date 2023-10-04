package com.example.gametaskforheadsandhands.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.gametaskforheadsandhands.data.EntitiesObject
import com.example.gametaskforheadsandhands.data.GameRepositoryImpl
import com.example.gametaskforheadsandhands.domain.entities.Level
import com.example.gametaskforheadsandhands.domain.usecases.LaunchValuesMonstersUseCase

class ChooseLevelViewModel : ViewModel() {
    private val repository = GameRepositoryImpl
    private val launchValuesMonstersUseCase = LaunchValuesMonstersUseCase(repository)
    fun launchValueMonsters(level: Level) {
        launchValuesMonstersUseCase.invoke(level)
    }
}