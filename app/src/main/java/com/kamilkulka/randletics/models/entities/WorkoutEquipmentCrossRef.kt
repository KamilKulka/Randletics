package com.kamilkulka.randletics.models.entities

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["workoutId","equipmentId"])
data class WorkoutEquipmentCrossRef(
    val workoutId: UUID,
    val equipmentId: UUID
)
