package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.data.EntitiesObject
import com.example.gametaskforheadsandhands.databinding.ActivityFinishRipGameBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityFinishRipGameBinding
class FinishRipGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_rip_game)
        binding = ActivityFinishRipGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRetryGame?.setOnClickListener {
            resetPointsHero()
            startActivity(StartGameActivity.newIntent(this))
        }
    }

    private fun resetPointsHero() {
        with(EntitiesObject.hero) {
            attack = 1
            defence = 1
            currentHealth = 1
            maxHealth = 1
            minDamage = 1
            maxDamage = 1
            countMedicalKit = 4
            countSkillsPoints= 10
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FinishRipGameActivity::class.java)
        }
    }
}