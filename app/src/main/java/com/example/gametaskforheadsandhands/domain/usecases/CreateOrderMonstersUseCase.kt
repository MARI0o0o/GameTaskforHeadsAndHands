package com.example.gametaskforheadsandhands.domain.usecases

import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.repositories.GameRepository
import java.util.LinkedList

class CreateOrderMonstersUseCase (private  val repository: GameRepository){
    operator fun invoke (): LinkedList<Entity> {
        return repository.createOrderMonsters()
    }
}