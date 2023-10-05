package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.data.EntitiesObject
import com.example.gametaskforheadsandhands.databinding.ActivityFinishWinGameBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityFinishWinGameBinding

class FinishWinGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_win_game)
        binding = ActivityFinishWinGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivContinue?.setOnClickListener {
            with(binding) {
                tvDescriptionWin2?.visibility = View.VISIBLE
                buttonRetryGame?.visibility = View.VISIBLE
                ivContinue?.visibility = View.GONE
                tvDescriptionWin1?.visibility = View.GONE
                ivWin?.visibility = View.GONE
                tvWin?.visibility = View.GONE
            }

        }
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
            countSkillsPoints = 10
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FinishWinGameActivity::class.java)
        }
    }
}