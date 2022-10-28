package com.kamilkulka.randletics.models.entities

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["exerciseId","equipmentId"])
data class ExerciseEquipmentCrossRef(
    val exerciseId: UUID,
    val equipmentId: UUID
)
