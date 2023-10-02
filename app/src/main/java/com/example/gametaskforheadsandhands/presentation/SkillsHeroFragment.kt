package com.example.gametaskforheadsandhands.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.databinding.FragmentSkillsHeroBinding
import com.example.gametaskforheadsandhands.domain.entities.Entity
import com.example.gametaskforheadsandhands.domain.entities.Hero
import com.example.gametaskforheadsandhands.domain.entities.HeroObject

class SkillsHeroFragment : Fragment() {

    private var _binding: FragmentSkillsHeroBinding? = null
    private val binding: FragmentSkillsHeroBinding
        get() = _binding ?: throw RuntimeException("FragmentSkillsHeroBinding == null")

    private lateinit var viewModel: SkillsHeroViewModel


//    private lateinit var symbolAttack: String
//    private lateinit var symbolDefence: String
//    private lateinit var symbolHealth: String
//    private lateinit var symbolMinDamage: String
//    private lateinit var symbolMaxDamage: String
//    private lateinit var symbolCountSkillPoints: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSkillsHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SkillsHeroViewModel::class.java]
//        viewModel = ViewModelProvider(
//            this,
//            SkillsHeroModelFactory(hero)
//        )[SkillsHeroViewModel::class.java]
//        launchValueToSymbols()
//        Log.d(
//            "GameActivityFragmentBeforelauchSkills",
//            "Герой: ${hero.name}, Скилл: ${hero.countSkillsPoints}, Атака: ${hero.attack} Защита: ${hero.defence} + Здоровье: ${hero.maxHealth} дамаг: ${hero.minDamage}-${hero.maxDamage} "
//        )
        launchSkills()
        clickListener()
    }

private fun clickListener() {
    binding.ivAttackIncrease.setOnClickListener {
        viewModel.increaseSkillAttack()
        launchSkillAttack()
        launchSkillPoints()
//        viewModel.countSkillAttack.observe(viewLifecycleOwner){
//            launchSkillAttack()
//            launchSkillPoints()
//        }
//        increaseSkillAttack()
    }
    binding.ivDefenceIncrease.setOnClickListener {
        viewModel.increaseSkillDefence()
        launchSkillDefence()
        launchSkillPoints()
//        viewModel.countSkillDefence.observe(viewLifecycleOwner){
//            launchSkillDefence()
//            launchSkillPoints()
//        }
//        increaseSkillDefence()
    }
    binding.ivHealthIncrease.setOnClickListener {
        viewModel.increaseSkillHealth()
        launchSkillHealth()
        launchSkillPoints()
//        viewModel.countSkillHealth.observe(viewLifecycleOwner){
//            launchSkillHealth()
//            launchSkillPoints()
//        }
//        increaseSkillHealth()
    }
    binding.ivMinDamageIncrease.setOnClickListener {
        viewModel.increaseSkillMinDamage()
            launchSkillMinDamage()
            launchSkillPoints()
//        viewModel.countSkillMinDamage.observe(viewLifecycleOwner){
//            launchSkillMinDamage()
//            launchSkillPoints()
//        }
//        increaseSkillMinDamage()
    }
    binding.ivMaxDamageIncrease.setOnClickListener {
        viewModel.increaseSkillMaxDamage()
        launchSkillMaxDamage()
        launchSkillPoints()
//        viewModel.countSkillMaxDamage.observe(viewLifecycleOwner){
//            launchSkillMaxDamage()
//            launchSkillPoints()
//        }
//        increaseSkillMaxDamage()
    }
}

//private fun increaseSkillAttack() {
//    if (hero.countSkillsPoints > 0 && hero.attack < Entity.MAX_VALUE_CHARACTERISTICS) {
//        hero.attack++
//        hero.countSkillsPoints--
//        launchSkillAttack()
//        launchSkillPoints()
//    }
//}
//
//private fun increaseSkillDefence() {
//    if (hero.countSkillsPoints > 0 && hero.defence < Entity.MAX_VALUE_CHARACTERISTICS) {
//        hero.defence++
//        hero.countSkillsPoints--
//        launchSkillDefence()
//        launchSkillPoints()
//    }
//}
//
//private fun increaseSkillHealth() {
//    if (hero.countSkillsPoints > 0) {
//        hero.maxHealth++
//        hero.countSkillsPoints--
//        launchSkillHealth()
//        launchSkillPoints()
//    }
//}
//
//private fun increaseSkillMinDamage() {
//    if (hero.countSkillsPoints > 0 && hero.minDamage < hero.maxDamage) {
//        hero.minDamage++
//        hero.countSkillsPoints--
//        launchSkillMinDamage()
//        launchSkillPoints()
//    }
//}
//
//private fun increaseSkillMaxDamage() {
//    if (hero.countSkillsPoints > 0) {
//        hero.maxDamage++
//        hero.countSkillsPoints--
//        launchSkillMaxDamage()
//        launchSkillPoints()
//    }
//}

private fun launchSkills() {
    launchSkillAttack()
    launchSkillDefence()
    launchSkillHealth()
    launchSkillMinDamage()
    launchSkillMaxDamage()
    launchSkillPoints()
}


private fun launchSkillAttack() {
//    binding.tvAttack.text = String.format(symbolAttack, hero.attack)
//    binding.tvAttack.text = "$STRING_ATTACK ${hero.attack}"
    binding.tvAttack.text = String.format(getString(R.string.attack_hero), HeroObject.hero.attack)
}


private fun launchSkillDefence() {
//    binding.tvDefence.text = String.format(symbolDefence, hero.defence)
    binding.tvDefence.text = String.format(getString(R.string.defence_hero), HeroObject.hero.defence)
}


private fun launchSkillHealth() {
//    binding.tvHealth.text = String.format(symbolHealth, hero.maxHealth)
    binding.tvHealth.text = String.format(getString(R.string.health_hero), HeroObject.hero.maxHealth)
}


private fun launchSkillMinDamage() {
//    binding.tvMinDamage.text = String.format(symbolMinDamage, hero.minDamage)
    binding.tvMinDamage.text = String.format(getString(R.string.min_damage_hero), HeroObject.hero.minDamage)
}

private fun launchSkillMaxDamage() {
//    binding.tvMaxDamage.text = String.format(symbolMaxDamage, hero.maxDamage)
    binding.tvMaxDamage.text = String.format(getString(R.string.max_damage_hero), HeroObject.hero.maxDamage)
}


    private fun launchSkillPoints() {
//        binding.tvCountSkills.text = String.format(symbolCountSkillPoints, hero.countSkillsPoints)
        binding.tvCountSkills.text = String.format(getString(R.string.count_skills), HeroObject.hero.countSkillsPoints)
    }

//private fun launchValueToSymbols() {
//    symbolAttack = this.resources.getString(R.string.attack_hero)
//    symbolDefence = this.resources.getString(R.string.defence_hero)
//    symbolHealth = this.resources.getString(R.string.health_hero)
//    symbolMinDamage = this.resources.getString(R.string.min_damage_hero)
//    symbolMaxDamage = this.resources.getString(R.string.max_damage_hero)
//    symbolCountSkillPoints = this.resources.getString(R.string.count_skills)
//}

//private fun parseArgs() {
//    requireArguments().getParcelable<Hero>(KEY_ARGUMENTS_HERO)?.let {
//        HeroObject.hero = it
////        hero = it
////        Log.d(
////            "GameActivityParseArgsFragment",
////            "Герой: ${hero.name}, Скилл: ${hero.countSkillsPoints}, Атака: ${hero.attack} Защита: ${hero.defence} + Здоровье: ${hero.maxHealth} дамаг: ${hero.minDamage}-${hero.maxDamage} "
////        )
//    }
//}


companion object {

    private const val KEY_ARGUMENTS_HERO = "hero"

    private const val STRING_ATTACK = "Атака:"
    private const val STRING_DEFENCE = "Защита:"
    private const val STRING_HEALTH = "Здоровье:"
    private const val STRING_MIN_DAMAGE = "Мин. урон:"
    private const val STRING_MAX_DAMAGE = "Макс. урон:"
    private const val STRING_COUNT_POINTS = "Очков навыка:"

//    fun newInstanceSkillsHero(hero: Hero): SkillsHeroFragment {
//        return SkillsHeroFragment().apply {
//            arguments = Bundle().apply {
//                putParcelable(KEY_ARGUMENTS_HERO, hero)
//            }
//        }

        fun newInstanceSkillsHero(): SkillsHeroFragment {
            return SkillsHeroFragment()
            }


    }
}
