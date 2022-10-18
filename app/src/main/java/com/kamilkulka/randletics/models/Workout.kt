package com.kamilkulka.randletics.models

import java.util.*

data class Workout(
    val id: UUID = UUID.randomUUID(),
    val title: String = "My Workout",
    val listOfExercises: List<Exercise> = emptyList(),
    val difficulty: Difficulty = Difficulty.MEDIUM
    )
