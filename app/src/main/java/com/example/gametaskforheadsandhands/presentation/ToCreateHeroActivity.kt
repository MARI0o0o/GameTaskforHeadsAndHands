package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.ActivityToCreateHeroBinding
import com.example.gametaskforheadsandhands.domain.entities.HeroObject


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityToCreateHeroBinding


class ToCreateHeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_create_hero)
        binding = ActivityToCreateHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchFragment()
        binding.buttonContinue.setOnClickListener {
            Log.d(
                "GameActivityToCreate",
                "Герой: ${HeroObject.hero.name}, Скилл: ${HeroObject.hero.countSkillsPoints}, Атака: ${HeroObject.hero.attack} Защита: ${HeroObject.hero.defence} + Здоровье: ${HeroObject.hero.maxHealth} дамаг: ${HeroObject.hero.minDamage}-${HeroObject.hero.maxDamage} "
            )
            continueGame()
        }
    }

    private fun continueGame() {
        val nameHero = binding.etNameHero.text.toString().trim()
        if (HeroObject.hero.countSkillsPoints == 0) {
            if (nameHero.isNotBlank()) {
                HeroObject.hero.name = nameHero
            }
            remoteFragment()
            startActivity(GameActivity.newIntent(this))
        }
        if (HeroObject.hero.countSkillsPoints > 0) {
            Toast.makeText(
                this,
                this.resources.getText(R.string.warn_have_skills_point),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.skills_hero_container, SkillsHeroFragment.newInstanceSkillsHero())
            .commit()
    }

    private fun remoteFragment() {
        supportFragmentManager.beginTransaction().remove(SkillsHeroFragment.newInstanceSkillsHero()).commit()
    }

    companion object {
        fun newIntent(contex: Context): Intent {
            return Intent(contex, ToCreateHeroActivity::class.java)
        }
    }
}