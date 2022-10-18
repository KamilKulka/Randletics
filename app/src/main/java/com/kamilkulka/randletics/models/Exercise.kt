package com.kamilkulka.randletics.models

import java.util.*

data class Exercise(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val multiplier: Float = 1f,
    val defaultRepeats: Int = 1,
    val muscleGroup: Muscle,
    val videoTutorial: String
)
