package com.example.gametaskforheadsandhands.domain.entities


class Hero(
    val name: String,
    var countMedicalKit: Int = COUNT_MEDICAL_KIT
) : Entity(name), MedicalKit {

    companion object {
        private const val COUNT_MEDICAL_KIT = 4
    }
}