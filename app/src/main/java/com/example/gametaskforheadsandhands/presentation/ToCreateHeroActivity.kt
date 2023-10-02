package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.ActivityToCreateHeroBinding
import com.example.gametaskforheadsandhands.domain.entities.Hero
import com.example.gametaskforheadsandhands.domain.entities.HeroObject

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityToCreateHeroBinding
private lateinit var hero: Hero

class ToCreateHeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_create_hero)
        binding = ActivityToCreateHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hero = HeroObject.hero
//        hero = Hero()
        launchFragment(hero)
        binding.buttonContinue.setOnClickListener {
            Log.d(
                "GameActivityToCreate",
                "Герой: ${hero.name}, Скилл: ${hero.countSkillsPoints}, Атака: ${hero.attack} Защита: ${hero.defence} + Здоровье: ${hero.maxHealth} дамаг: ${hero.minDamage}-${hero.maxDamage} "
            )
            continueGame()
        }
    }

    private fun continueGame() {
        val nameHero = binding.etNameHero.text.toString().trim()
        if (hero.countSkillsPoints == 0) {
            if (nameHero.isNotBlank()) {
                hero.name = nameHero
            }
            startActivity(GameActivity.newIntent(this, hero.attack, hero.defence, hero.currentHealth, hero.minDamage, hero.maxDamage, hero.name, hero.countSkillsPoints))
        }
        if (hero.countSkillsPoints > 0) {
            Toast.makeText(
                this,
                this.resources.getText(R.string.warn_have_skills_point),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun launchFragment(hero: Hero) {
        supportFragmentManager.beginTransaction()
            .add(R.id.skills_hero_container, SkillsHeroFragment.newInstanceSkillsHero(hero))
            .commit()
    }

    companion object {

        fun newIntent(contex: Context): Intent {
            return Intent(contex, ToCreateHeroActivity::class.java)
        }
    }
}