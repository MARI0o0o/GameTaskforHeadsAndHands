package com.example.gametaskforheadsandhands.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Hero
    (
    override var name: String = DEFAULT_NAME,
    var countSkillsPoints: Int = COUNT_SKILLS_POINT,
    var countMedicalKit: Int = COUNT_MEDICAL_KIT
)
    : Entity(name), Parcelable {



    companion object {
        private const val COUNT_MEDICAL_KIT = 4
        private const val COUNT_SKILLS_POINT = 10
        private const val DEFAULT_NAME = "Boy"
    }

}