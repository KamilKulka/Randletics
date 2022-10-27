package com.kamilkulka.randletics.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.Muscle
import java.util.*
@Entity
data class Exercise(
    @PrimaryKey
    val exerciseId: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "exercise_name")
    val name: String,
    @ColumnInfo(name = "exercise_multiplier")
    var multiplier: Float = 1f,
    @ColumnInfo(name = "exercise_default_repeats")
    var defaultRepeats: Int = 1,
    @ColumnInfo(name = "exercise_muscle")
    val muscleGroup: Muscle,
    @ColumnInfo(name = "exercise_tutorial")
    val videoTutorial: String,
    @ColumnInfo(name = "exercise_difficulty")
    val difficulty: Difficulty = Difficulty.EASY
)
