package com.example.gametaskforheadsandhands.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gametaskforheadsandhands.R
import com.example.gametaskforheadsandhands.data.EntitiesObject
import com.example.gametaskforheadsandhands.databinding.FragmentSkillsHeroBinding
import com.example.gametaskforheadsandhands.presentation.viewModel.SkillsHeroViewModel

class SkillsHeroFragment : Fragment() {

    private var _binding: FragmentSkillsHeroBinding? = null
    private val binding: FragmentSkillsHeroBinding
        get() = _binding ?: throw RuntimeException("FragmentSkillsHeroBinding == null")

    private lateinit var viewModel: SkillsHeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        launchSkills()
        clickListener()
    }

    private fun clickListener() {
        with(binding) {
            ivAttackIncrease.setOnClickListener {
                viewModel.increaseSkillAttack()
                launchSkillAttack()
                launchSkillPoints()
            }
            ivDefenceIncrease.setOnClickListener {
                viewModel.increaseSkillDefence()
                launchSkillDefence()
                launchSkillPoints()
            }
            ivHealthIncrease.setOnClickListener {
                viewModel.increaseSkillHealth()
                launchSkillHealth()
                launchSkillPoints()
                EntitiesObject.hero.currentHealth = EntitiesObject.hero.maxHealth
            }
            ivMinDamageIncrease.setOnClickListener {
                viewModel.increaseSkillMinDamage()
                launchSkillMinDamage()
                launchSkillPoints()
            }
            ivMaxDamageIncrease.setOnClickListener {
                viewModel.increaseSkillMaxDamage()
                launchSkillMaxDamage()
                launchSkillPoints()
            }
        }
    }

    private fun launchSkills() {
        launchSkillAttack()
        launchSkillDefence()
        launchSkillHealth()
        launchSkillMinDamage()
        launchSkillMaxDamage()
        launchSkillPoints()
    }


    private fun launchSkillAttack() {
        binding.tvAttack.text =
            String.format(getString(R.string.attack_hero), EntitiesObject.hero.attack)
    }


    private fun launchSkillDefence() {
        binding.tvDefence.text =
            String.format(getString(R.string.defence_hero), EntitiesObject.hero.defence)
    }


    private fun launchSkillHealth() {
        binding.tvHealth.text =
            String.format(getString(R.string.health_hero), EntitiesObject.hero.maxHealth)
    }


    private fun launchSkillMinDamage() {
        binding.tvMinDamage.text =
            String.format(getString(R.string.min_damage_hero), EntitiesObject.hero.minDamage)
    }

    private fun launchSkillMaxDamage() {
        binding.tvMaxDamage.text =
            String.format(getString(R.string.max_damage_hero), EntitiesObject.hero.maxDamage)
    }


    private fun launchSkillPoints() {
        binding.tvCountSkills.text =
            String.format(getString(R.string.count_skills), EntitiesObject.hero.countSkillsPoints)
    }


    companion object {

        fun newInstanceSkillsHero(): SkillsHeroFragment {
            return SkillsHeroFragment()
        }
    }
}
