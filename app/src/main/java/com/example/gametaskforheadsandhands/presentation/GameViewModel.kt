package com.example.gametaskforheadsandhands.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gametaskforheadsandhands.data.GameRepositoryImpl
import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.HeroObject
import com.example.gametaskforheadsandhands.domain.usecases.CreateOrderMonstersUseCase
import com.example.gametaskforheadsandhands.domain.usecases.HitUseCase
import com.example.gametaskforheadsandhands.domain.usecases.MedicalKitUseCase
import java.util.LinkedList

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GameRepositoryImpl
    private val hitUseCase = HitUseCase(repository)
    private val medicalKitUseCase = MedicalKitUseCase(repository)
    private val createOrderMonstersUseCase = CreateOrderMonstersUseCase(repository)


    var battleMode: Boolean = true

    //    true - hero strike, false - monster strike
    private val _whoseHit = MutableLiveData<Boolean>()
    val whoseHit: LiveData<Boolean>
        get() = _whoseHit

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

    private val _pointHealthHero = MutableLiveData<Int>()
    val pointHealthHero: LiveData<Int>
        get() = _pointHealthHero

    private val _winGame = MutableLiveData<Boolean>()
    val winGame: LiveData<Boolean>
        get() = _winGame

    private val _RipGame = MutableLiveData<Boolean>()
    val RipGame: LiveData<Boolean>
        get() = _RipGame

    private var Monsters: LinkedList<Entity> = createOrderMonstersUseCase()

    init {
        getValueMonster(Monsters[0])
        _whoseHit.value = true
    }

    fun gameProcess() {
        val monster = Monsters[0]
        _nameMonster.value = monster.name
        while (monster.currentHealth != 0 || HeroObject.hero.currentHealth != 0) {
            if (_whoseHit.value == true) {
                strikeHero(monster)
                break
            }
            if (_whoseHit.value == false) {
                strikeMonster(monster)
            }
        }
    }

    private fun strikeHero(monster: Entity) {
        val hitPoint = hitUseCase(
            HeroObject.hero.attack,
            monster.defence,
            HeroObject.hero.minDamage,
            HeroObject.hero.maxDamage,
            monster.currentHealth
        )
        monster.currentHealth = monster.currentHealth - hitPoint
        _pointHealthMonster.value = monster.currentHealth
        if (monster.currentHealth == 0) {
            _nameDeadMonster.value = monster.name
            Monsters.remove(monster)
            if (Monsters.isEmpty()) {
                _winGame.value = true
            }
            HeroObject.hero.countSkillsPoints = SKILL_POINTS_AFTER_KILL
            switchBattleMode()
            if (Monsters.isNotEmpty()) {
                _nameMonster.value = Monsters[0].name
            }
        }
        else {
            _whoseHit.value = false
        }
    }

    private fun strikeMonster(monster: Entity) {
        val hitPoint = hitUseCase(
            monster.attack,
            HeroObject.hero.defence,
            monster.minDamage,
            monster.maxDamage,
            HeroObject.hero.currentHealth
        )
        HeroObject.hero.currentHealth = HeroObject.hero.currentHealth - hitPoint
        _pointHealthHero.value = HeroObject.hero.currentHealth
        if (HeroObject.hero.currentHealth == 0) {
            _RipGame.value = true
        }
        if (HeroObject.hero.currentHealth != 0) {
            _whoseHit.value = true
        }
    }

    fun useMedicalKit() {
        HeroObject.hero.currentHealth = medicalKitUseCase(HeroObject.hero.currentHealth, HeroObject.hero.maxHealth)
        HeroObject.hero.countMedicalKit--
        _pointHealthHero.value = HeroObject.hero.currentHealth
    }

    fun switchBattleMode() {
        battleMode = !battleMode
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
        private const val SKILL_POINTS_AFTER_KILL = 5
        private const val DELAY_TIME_MS = 1000
    }

}