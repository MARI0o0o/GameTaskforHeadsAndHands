package com.example.gametaskforheadsandhands.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gametaskforheadsandhands.R

class FinishRipGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_rip_game)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FinishRipGameActivity::class.java)
        }
    }
}