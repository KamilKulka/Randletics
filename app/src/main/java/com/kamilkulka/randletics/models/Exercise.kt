package com.kamilkulka.randletics.models

data class Exercise(
    val name: String,
    val multiplier: Float = 1f,
    val defaultRepeats: Int = 1,
    val muscleGroup: Muscle,
    val videoTutorial: String
)
