package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.ActivityChooseLevelBinding
import com.example.gametaskforheadsandhands.domain.entities.Level
import com.example.gametaskforheadsandhands.presentation.viewModel.ChooseLevelViewModel


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityChooseLevelBinding

class ChooseLevelActivity : AppCompatActivity() {
    private lateinit var viewModel: ChooseLevelViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)
        viewModel = ViewModelProvider(this)[ChooseLevelViewModel::class.java]
        binding = ActivityChooseLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonEasy.setOnClickListener {
            viewModel.launchValueMonsters(Level.EASY)
            startActivity(ToCreateHeroActivity.newIntent(this))
        }
        binding.buttonMedium.setOnClickListener {
            viewModel.launchValueMonsters(Level.NORMAL)
            startActivity(ToCreateHeroActivity.newIntent(this))
        }
        binding.buttonHard.setOnClickListener {
            viewModel.launchValueMonsters(Level.HARD)
            startActivity(ToCreateHeroActivity.newIntent(this))
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChooseLevelActivity::class.java)
        }
    }
}
