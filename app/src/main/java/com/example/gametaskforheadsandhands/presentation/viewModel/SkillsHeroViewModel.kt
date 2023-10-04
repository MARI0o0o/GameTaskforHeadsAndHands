package com.example.gametaskforheadsandhands.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.data.EntitiesObject

class SkillsHeroViewModel() : ViewModel() {

    fun increaseSkillAttack() {
        if (EntitiesObject.hero.countSkillsPoints > 0 && EntitiesObject.hero.attack < Entity.MAX_VALUE_CHARACTERISTICS) {
            EntitiesObject.hero.attack++
            EntitiesObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillDefence() {
        if (EntitiesObject.hero.countSkillsPoints > 0 && EntitiesObject.hero.defence < Entity.MAX_VALUE_CHARACTERISTICS) {
            EntitiesObject.hero.defence++
            EntitiesObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillHealth() {
        if (EntitiesObject.hero.countSkillsPoints > 0) {
            EntitiesObject.hero.maxHealth++
            EntitiesObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillMinDamage() {
        if (EntitiesObject.hero.countSkillsPoints > 0 && EntitiesObject.hero.minDamage < EntitiesObject.hero.maxDamage) {
            EntitiesObject.hero.minDamage++
            EntitiesObject.hero.countSkillsPoints--
        }
    }

    fun increaseSkillMaxDamage() {
        if (EntitiesObject.hero.countSkillsPoints > 0) {
            EntitiesObject.hero.maxDamage++
            EntitiesObject.hero.countSkillsPoints--
        }
    }
}