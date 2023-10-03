package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.ActivityGameBinding
import com.example.gametaskforheadsandhands.domain.entities.HeroObject
import com.example.gametaskforheadsandhands.domain.entities.monsters.Monsters

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.skillsHeroContainer?.visibility = View.INVISIBLE
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        observeViewModel()
        launchElements()
        Log.d(
            "GameActivityGame",
            "Герой: ${HeroObject.hero.name}, Скилл: ${HeroObject.hero.countSkillsPoints}, Атака: ${HeroObject.hero.attack} Защита: ${HeroObject.hero.defence} + Здоровье: ${HeroObject.hero.maxHealth} дамаг: ${HeroObject.hero.minDamage}-${HeroObject.hero.maxDamage} "
        )
       buttonClickListener()
    }

    private fun buttonClickListener() {
        binding.buttonContinueBattle?.setOnClickListener {
            if (HeroObject.hero.countSkillsPoints == 0 && !viewModel.battleMode) {
                viewModel.switchBattleMode()
                remoteFragment()
                launchPictureMonster(viewModel.nameMonster.value.toString())
                launchElements()
                binding.buttonContinueBattle?.text = "Начать бой!"
            }
            else if (HeroObject.hero.countSkillsPoints == 0) {
                viewModel.gameProcess()
            }
            else {
                Toast.makeText(
                    this,
                    this.resources.getText(R.string.warn_have_skills_point),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.buttonHeal?.setOnClickListener {
            if (HeroObject.hero.countMedicalKit > 0) {
                viewModel.useMedicalKit()
            }
            else {
                Toast.makeText(
                    this,
                    this.resources.getText(R.string.warn_no_have_health_point),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun observeViewModel () {
        viewModel.nameDeadMonster.observe(this, Observer{
            binding.buttonContinueBattle?.text = "Продолжить"
            launchFragment()
            hidePictureMonster(it)

        })
        viewModel.pointHealthHero.observe(this, Observer{
            launchSkillHealth()
        })
        viewModel.pointHealthMonster.observe(this, Observer{
            launchSkillHealth()
        })
        viewModel.winGame.observe(this, Observer {
            if (it) {
                startActivity(FinishWinGameActivity.newIntent(this))
            }
        })
        viewModel.RipGame.observe(this, Observer{
            if (it) {
                startActivity(FinishRipGameActivity.newIntent(this))
            }
        })
        viewModel.whoseHit.observe(this, Observer{
            if (it) {
                binding.tvWhoseStrike?.text = "Ваш ход"
            }
            else {
                binding.tvWhoseStrike?.text = "Ход противника"
            }
        })
    }

    private fun launchPictureMonster(monster: String) {
        when (monster){
            Monsters.GOBLIN.value ->{
                binding.ivOrc.visibility = View.VISIBLE
                launchVisible()
            }
            Monsters.POISON_FLOWER.value ->{
                binding.ivFlower.visibility = View.VISIBLE
                launchVisible()
            }
            Monsters.WATER_MONSTER.value ->{
                binding.ivWaterMonster.visibility = View.VISIBLE
                launchVisible()
            }
            Monsters.DRAGON.value ->{
                binding.ivDragon.visibility = View.VISIBLE
                launchVisible()
            }
        }
    }

    private fun hidePictureMonster(monster: String) {
        when (monster){
            Monsters.GOBLIN.value ->{
                binding.ivOrc.visibility = View.INVISIBLE
                launchInvisible()
            }
            Monsters.POISON_FLOWER.value ->{
                binding.ivFlower.visibility = View.INVISIBLE
                launchInvisible()
            }
            Monsters.WATER_MONSTER.value ->{
                binding.ivWaterMonster.visibility = View.INVISIBLE
                launchInvisible()
            }
            Monsters.DRAGON.value ->{
                binding.ivDragon.visibility = View.INVISIBLE
                launchInvisible()
            }
        }
    }
    private fun launchVisible() {
        with(binding) {
            tvMonsterName?.visibility = View.VISIBLE
            tvAttackEntities?.visibility = View.VISIBLE
            tvDefenceEntities?.visibility = View.VISIBLE
            tvHealthEntities?.visibility = View.VISIBLE
            tvDamageEntities?.visibility = View.VISIBLE
            skillsHeroContainer?.visibility = View.INVISIBLE
        }
    }

    private fun launchInvisible() {
        with(binding) {
            tvMonsterName?.visibility = View.INVISIBLE
            tvAttackEntities?.visibility = View.INVISIBLE
            tvDefenceEntities?.visibility = View.INVISIBLE
            tvHealthEntities?.visibility = View.INVISIBLE
            tvDamageEntities?.visibility = View.INVISIBLE
            skillsHeroContainer?.visibility = View.VISIBLE
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

    private fun launchElements() {
        launchSkillAttack()
        launchSkillDefence()
        launchSkillHealth()
        launchSkillDamage()
        launchHeroName()
        launchMonsterName()
        launchPictureMonster(viewModel.nameMonster.value.toString())
    }

    private fun launchSkillAttack() {
        binding.tvAttackEntities?.text = String.format(
            getString(R.string.attack_entities),
            HeroObject.hero.attack,
            viewModel.pointAttackMonster.value
        )
    }

    private fun launchSkillDefence() {
        binding.tvDefenceEntities?.text = String.format(
            getString(R.string.defence_entities),
            HeroObject.hero.defence,
            viewModel.pointDefenceMonster.value
        )
    }

    private fun launchSkillHealth() {
        binding.tvHealthEntities?.text = String.format(
            getString(R.string.health_entities),
            HeroObject.hero.currentHealth,
            viewModel.pointHealthMonster.value
        )
    }

    private fun launchSkillDamage() {
        binding.tvDamageEntities?.text = String.format(
            getString(R.string.damage_entities),
            HeroObject.hero.minDamage,
            HeroObject.hero.maxDamage,
            viewModel.pointMinDamageMonster.value,
            viewModel.pointMaxDamageMonster.value
        )
    }

    private fun launchHeroName() {
        binding.tvHeroName?.text = String.format(
            getString(R.string.hero_name),
            HeroObject.hero.name,
        )
    }

    private fun launchMonsterName() {
        binding.tvMonsterName?.text = String.format(
            viewModel.nameMonster.value.toString()
        )
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GameActivity::class.java)
        }
    }
}


