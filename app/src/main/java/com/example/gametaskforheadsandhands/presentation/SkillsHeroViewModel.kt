package com.example.gametaskforheadsandhands.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.Hero

class SkillsHeroViewModel(private var hero: Hero) : ViewModel() {


//    private val _countSkillPoints = MutableLiveData<Int>()
//    val countSkillPoints: LiveData<Int>
//        get() = _countSkillPoints
//
//    private val _countSkillAttack = MutableLiveData<Int>()
//    val countSkillAttack: LiveData<Int>
//        get() = _countSkillAttack
//
//    private val _countSkillDefence = MutableLiveData<Int>()
//    val countSkillDefence: LiveData<Int>
//        get() = _countSkillDefence
//
//    private val _countSkillHealth = MutableLiveData<Int>()
//    val countSkillHealth: LiveData<Int>
//        get() = _countSkillHealth
//
//    private val _countSkillMinDamage = MutableLiveData<Int>()
//    val countSkillMinDamage: LiveData<Int>
//        get() = _countSkillMinDamage
//
//    private val _countSkillMaxDamage = MutableLiveData<Int>()
//    val countSkillMaxDamage: LiveData<Int>
//        get() = _countSkillMaxDamage


    fun increaseSkillAttack() {
        if (hero.countSkillsPoints > 0 && hero.attack < Entity.MAX_VALUE_CHARACTERISTICS) {
            hero.attack++
            hero.countSkillsPoints--
//            _countSkillAttack.value = hero.attack
//            _countSkillPoints.value = hero.countSkillsPoints
        }
    }

    fun increaseSkillDefence() {
        if (hero.countSkillsPoints > 0 && hero.defence < Entity.MAX_VALUE_CHARACTERISTICS) {
            hero.defence++
            hero.countSkillsPoints--
//            _countSkillDefence.value = hero.defence
//            _countSkillPoints.value = hero.countSkillsPoints
        }
    }

    fun increaseSkillHealth() {
        if (hero.countSkillsPoints > 0) {
            hero.maxHealth++
            hero.countSkillsPoints--
//            _countSkillHealth.value = hero.maxHealth
//            _countSkillPoints.value = hero.countSkillsPoints
        }
    }

    fun increaseSkillMinDamage() {
        if (hero.countSkillsPoints > 0 && hero.minDamage < hero.maxDamage) {
            hero.minDamage++
            hero.countSkillsPoints--
//            _countSkillMinDamage.value = hero.minDamage
//            _countSkillPoints.value = hero.countSkillsPoints
        }
    }

    fun increaseSkillMaxDamage() {
        if (hero.countSkillsPoints > 0) {
            hero.maxDamage++
            hero.countSkillsPoints--
//            _countSkillMaxDamage.value = hero.maxDamage
//            _countSkillPoints.value = hero.countSkillsPoints
        }
    }
}