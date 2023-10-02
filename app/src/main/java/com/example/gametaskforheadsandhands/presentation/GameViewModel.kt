package com.example.gametaskforheadsandhands.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gametaskforheadsandhands.data.GameRepositoryImpl
import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.Hero
import com.example.gametaskforheadsandhands.domain.entities.HeroObject
import com.example.gametaskforheadsandhands.domain.usecases.CreateOrderMonstersUseCase
import com.example.gametaskforheadsandhands.domain.usecases.HitUseCase
import com.example.gametaskforheadsandhands.domain.usecases.MedicalKitUseCase
import java.util.LinkedList

class GameViewModel() : ViewModel() {

    private val repository = GameRepositoryImpl
    private val hitUseCase = HitUseCase(repository)
    private val medicalKitUseCase = MedicalKitUseCase(repository)
    private val createOrderMonstersUseCase = CreateOrderMonstersUseCase(repository)

//    true - hero strike, false - monster strike
    private var whoseHit: Boolean = true

    private val _pointAttackMonster = MutableLiveData<Int>()
    val pointAttackMonster: LiveData<Int>
        get() = _pointAttackMonster

    private val _pointDefenceMonster = MutableLiveData<Int>()
    val pointDefenceMonster: LiveData<Int>
        get() = _pointDefenceMonster

    private val _pointHealthMonster = MutableLiveData<Int>()
    val pointHealthMonster: LiveData<Int>
        get() = _pointHealthMonster

    private val _pointMinDamageMonster = MutableLiveData<Int>()
    val pointMinDamageMonster: LiveData<Int>
        get() = _pointMinDamageMonster

    private val _pointMaxDamageMonster = MutableLiveData<Int>()
    val pointMaxDamageMonster: LiveData<Int>
        get() = _pointMaxDamageMonster

    private val _nameMonster = MutableLiveData<String>()
    val nameMonster: LiveData<String>
        get() = _nameMonster

    private val _nameDeadMonster = MutableLiveData<String>()
    val nameDeadMonster: LiveData<String>
        get() = _nameDeadMonster

    private val _winGame = MutableLiveData<Boolean>()
    val winGame: LiveData<Boolean>
        get() = _winGame

    private var Monsters: LinkedList<Entity> = createOrderMonstersUseCase()

    init {
        getValueMonster(Monsters[0])
    }

    fun gameProcess() {
        while (Monsters.isNotEmpty() || HeroObject.hero.currentHealth!=0) {
            for (monster in Monsters) {
                if (whoseHit) {
                    val hitPoint = hitUseCase(HeroObject.hero.attack, monster.defence, HeroObject.hero.minDamage, HeroObject.hero.maxDamage, monster.currentHealth)
                    monster.currentHealth = monster.currentHealth - hitPoint
                    if (monster.currentHealth == 0) {
                        _nameDeadMonster.value = monster.name
                        Monsters.remove(monster)
                        HeroObject.hero.countSkillsPoints = SKILL_POINTS_AFTER_KILL
                        whoseHit = !whoseHit
                    }
                }

                if (!whoseHit) {
                    val hitPoint = hitUseCase(monster.attack, HeroObject.hero.defence, monster.minDamage, monster.maxDamage, HeroObject.hero.currentHealth)
                    HeroObject.hero.currentHealth = HeroObject.hero.currentHealth - hitPoint
                    if (HeroObject.hero.currentHealth != 0) {
                        whoseHit = !whoseHit
                    }
                }
            }
        }
        if (Monsters.isEmpty()) {
            _winGame.value = true
        }
        if (HeroObject.hero.currentHealth == 0) {
            _winGame.value = false
        }
    }
    private fun getValueMonster (monster: Entity) {
        _pointAttackMonster.value = monster.attack
        _pointDefenceMonster.value = monster.defence
        _pointHealthMonster.value = monster.currentHealth
        _pointMinDamageMonster.value = monster.minDamage
        _pointMaxDamageMonster.value = monster.maxDamage
        _nameMonster.value = monster.name
    }

    companion object {
        private const val SKILL_POINTS_AFTER_KILL = 10
    }

}