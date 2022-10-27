package com.kamilkulka.randletics.models.entities

import androidx.room.Entity
import java.util.UUID

@Entity(primaryKeys = ["workoutId","exerciseId"])
data class WorkoutExerciseCrossRef(
    val workoutId: UUID,
    val exerciseId: UUID
)
