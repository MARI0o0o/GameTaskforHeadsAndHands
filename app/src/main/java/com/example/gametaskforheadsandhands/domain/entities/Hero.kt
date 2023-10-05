package com.example.gametaskforheadsandhands.domain.entities

class Hero
    (
    override var name: String = DEFAULT_NAME,
    var countSkillsPoints: Int = COUNT_SKILLS_POINT,
    var countMedicalKit: Int = COUNT_MEDICAL_KIT
) : Entity(name) {


    companion object {
        private const val COUNT_MEDICAL_KIT = 4
        private const val COUNT_SKILLS_POINT = 10
        private const val DEFAULT_NAME = "Boy"
    }

}