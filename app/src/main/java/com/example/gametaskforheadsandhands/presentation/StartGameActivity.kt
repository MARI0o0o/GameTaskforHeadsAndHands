package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.ActivityStartGameBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityStartGameBinding
class StartGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            startActivity(ChooseLevelActivity.newIntent(this))
        }
    }

    companion object {
        fun newIntent(context: Context):Intent {
            return Intent(context, StartGameActivity::class.java)
        }
    }
}