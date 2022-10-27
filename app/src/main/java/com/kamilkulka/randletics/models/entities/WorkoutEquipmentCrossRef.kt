package com.kamilkulka.randletics.models.entities

import androidx.room.Entity
import com.kamilkulka.randletics.models.EquipmentType
import java.util.*

@Entity(primaryKeys = ["workoutId","equipmentType"])
data class WorkoutEquipmentCrossRef(
    val workoutId: UUID,
    val equipmentType: EquipmentType
)
