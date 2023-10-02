package com.example.gametaskforheadsandhands.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.ActivityGameBinding
import com.example.gametaskforheadsandhands.domain.entities.Hero
import com.example.gametaskforheadsandhands.domain.entities.HeroObject
import com.example.gametaskforheadsandhands.domain.entities.monsters.Monsters

class GameActivity : AppCompatActivity() {
    private lateinit var viewModel: GameViewModel

    private var _binding: ActivityGameBinding? = null
    private val binding: ActivityGameBinding
        get() = _binding ?: throw RuntimeException("ActivityGameBinding == null")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        _binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        parseIntent()
//        Log.d(
//            "GameActivityGame",
//            "Герой: ${hero.name}, Скилл: ${hero.countSkillsPoints}, Атака: ${hero.attack} Защита: ${hero.defence} + Здоровье: ${hero.maxHealth} дамаг: ${hero.minDamage}-${hero.maxDamage} "
//        )
//        viewModel = ViewModelProvider(
//            this,
//            SkillsHeroModelFactory(hero)
//        )[GameViewModel::class.java]
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        launchFirstElements()
        observeViewModel()
        binding.buttonContinue?.setOnClickListener {
            viewModel.gameProcess()
        }
    }

    private fun observeViewModel () {
        viewModel.nameMonster.observe(this, Observer {
            launchPictureMonster(it)
            binding.skillsHeroContainer?.visibility = View.INVISIBLE
        })
        viewModel.nameDeadMonster.observe(this, Observer{
            hidePictureMonster(it)
            launchFragment()
            binding.skillsHeroContainer?.visibility = View.VISIBLE

        })
        viewModel.winGame.observe(this, Observer{
            if (it) {
                startActivity(FinishWinGameActivity.newIntent(this))
            }
            if(!it) {
                startActivity(FinishRipGameActivity.newIntent(this))
            }
        })
    }

    private fun launchPictureMonster(monster: String) {
        when (monster){
            Monsters.GOBLIN.value ->{
                binding.ivOrc.visibility = View.VISIBLE
                binding.tvMonsterName?.visibility = View.VISIBLE
                binding.tvAttackEntities?.visibility = View.VISIBLE
                binding.tvDefenceEntities?.visibility = View.VISIBLE
                binding.tvHealthEntities?.visibility = View.VISIBLE
                binding.tvDamageEntities?.visibility = View.VISIBLE
            }
            Monsters.POISON_FLOWER.value ->{
                binding.ivFlower.visibility = View.VISIBLE
                binding.tvMonsterName?.visibility = View.VISIBLE
                binding.tvAttackEntities?.visibility = View.VISIBLE
                binding.tvDefenceEntities?.visibility = View.VISIBLE
                binding.tvHealthEntities?.visibility = View.VISIBLE
                binding.tvDamageEntities?.visibility = View.VISIBLE
            }
            Monsters.WATER_MONSTER.value ->{
                binding.ivWaterMonster.visibility = View.VISIBLE
                binding.tvMonsterName?.visibility = View.VISIBLE
                binding.tvAttackEntities?.visibility = View.VISIBLE
                binding.tvDefenceEntities?.visibility = View.VISIBLE
                binding.tvHealthEntities?.visibility = View.VISIBLE
                binding.tvDamageEntities?.visibility = View.VISIBLE
            }
            Monsters.DRAGON.value ->{
                binding.ivDragon.visibility = View.VISIBLE
                binding.tvMonsterName?.visibility = View.VISIBLE
                binding.tvAttackEntities?.visibility = View.VISIBLE
                binding.tvDefenceEntities?.visibility = View.VISIBLE
                binding.tvHealthEntities?.visibility = View.VISIBLE
                binding.tvDamageEntities?.visibility = View.VISIBLE
            }
        }
    }

    private fun hidePictureMonster(monster: String) {
        when (monster){
            Monsters.GOBLIN.value ->{
                binding.ivOrc.visibility = View.INVISIBLE
                binding.tvMonsterName?.visibility = View.INVISIBLE
                binding.tvAttackEntities?.visibility = View.INVISIBLE
                binding.tvDefenceEntities?.visibility = View.INVISIBLE
                binding.tvHealthEntities?.visibility = View.INVISIBLE
                binding.tvDamageEntities?.visibility = View.INVISIBLE
            }
            Monsters.POISON_FLOWER.value ->{
                binding.ivFlower.visibility = View.INVISIBLE
                binding.tvMonsterName?.visibility = View.INVISIBLE
                binding.tvAttackEntities?.visibility = View.INVISIBLE
                binding.tvDefenceEntities?.visibility = View.INVISIBLE
                binding.tvHealthEntities?.visibility = View.INVISIBLE
                binding.tvDamageEntities?.visibility = View.INVISIBLE
            }
            Monsters.WATER_MONSTER.value ->{
                binding.ivWaterMonster.visibility = View.INVISIBLE
                binding.tvMonsterName?.visibility = View.INVISIBLE
                binding.tvAttackEntities?.visibility = View.INVISIBLE
                binding.tvDefenceEntities?.visibility = View.INVISIBLE
                binding.tvHealthEntities?.visibility = View.INVISIBLE
                binding.tvDamageEntities?.visibility = View.INVISIBLE
            }
            Monsters.DRAGON.value ->{
                binding.ivDragon.visibility = View.INVISIBLE
                binding.tvMonsterName?.visibility = View.INVISIBLE
                binding.tvAttackEntities?.visibility = View.INVISIBLE
                binding.tvDefenceEntities?.visibility = View.INVISIBLE
                binding.tvHealthEntities?.visibility = View.INVISIBLE
                binding.tvDamageEntities?.visibility = View.INVISIBLE
            }
        }
    }
//    private fun parseIntent() {
//        hero.attack = intent.getIntExtra(KEY_ATTACK, 1)
//        hero.defence = intent.getIntExtra(KEY_DEFENCE, 1)
//        hero.maxHealth = intent.getIntExtra(KEY_HEALTH, 1)
//        hero.minDamage = intent.getIntExtra(KEY_MIN_DAMAGE, 1)
//        hero.maxDamage = intent.getIntExtra(KEY_MIN_DAMAGE, 1)
//        hero.name = intent.getStringExtra(KEY_NAME).toString()
//        hero.countSkillsPoints = intent.getIntExtra(KEY_SKILL_POINTS, 1)
//    }

//    private fun launchFragment(hero: Hero) {
//        supportFragmentManager.beginTransaction()
//            .add(R.id.skills_hero_container, SkillsHeroFragment.newInstanceSkillsHero(hero))
//            .commit()
//    }

    private fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.skills_hero_container, SkillsHeroFragment.newInstanceSkillsHero())
            .commit()
    }

    private fun launchFirstElements() {
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
            getString(R.string.attack_entities),
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
        private const val KEY_ATTACK = "attack"
        private const val KEY_DEFENCE = "defence"
        private const val KEY_HEALTH = "health"
        private const val KEY_MIN_DAMAGE= "min_damage"
        private const val KEY_MAX_DAMAGE = "max_damage"
        private const val KEY_NAME = "name"
        private const val KEY_SKILL_POINTS = "skill_points"
//        fun newIntent(context: Context, attack: Int, defence: Int, health: Int, minDamage: Int, maxDamage: Int, name: String, skillPoints: Int): Intent {
//            val intent = Intent(context, GameActivity::class.java)
//            intent.putExtra(KEY_ATTACK, attack)
//            intent.putExtra(KEY_DEFENCE, defence)
//            intent.putExtra(KEY_HEALTH, health)
//            intent.putExtra(KEY_MIN_DAMAGE, minDamage)
//            intent.putExtra(KEY_MAX_DAMAGE, maxDamage)
//            intent.putExtra(KEY_NAME, name)
//            intent.putExtra(KEY_SKILL_POINTS, skillPoints)
//            return intent
//        }

        fun newIntent(context: Context): Intent {
            return Intent(context, GameActivity::class.java)
        }
    }
}


