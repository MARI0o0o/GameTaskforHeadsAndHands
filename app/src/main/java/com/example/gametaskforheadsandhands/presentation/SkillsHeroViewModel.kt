package com.example.gametaskforheadsandhands.presentation

import androidx.lifecycle.ViewModel
import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.HeroObject

class SkillsHeroViewModel() : ViewModel() {

    fun increaseSkillAttack() {
        if (HeroObject.hero.countSkillsPoints > 0 && HeroObject.hero.attack < Entity.MAX_VALUE_CHARACTERISTICS) {
            HeroObject.hero.attack++
            HeroObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillDefence() {
        if (HeroObject.hero.countSkillsPoints > 0 && HeroObject.hero.defence < Entity.MAX_VALUE_CHARACTERISTICS) {
            HeroObject.hero.defence++
            HeroObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillHealth() {
        if (HeroObject.hero.countSkillsPoints > 0) {
            HeroObject.hero.maxHealth++
            HeroObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillMinDamage() {
        if (HeroObject.hero.countSkillsPoints > 0 && HeroObject.hero.minDamage < HeroObject.hero.maxDamage) {
            HeroObject.hero.minDamage++
            HeroObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillMaxDamage() {
        if (HeroObject.hero.countSkillsPoints > 0) {
            HeroObject.hero.maxDamage++
            HeroObject.hero.countSkillsPoints--
        }
    }
}