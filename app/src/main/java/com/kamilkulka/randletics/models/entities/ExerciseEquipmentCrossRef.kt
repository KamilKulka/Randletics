package com.kamilkulka.randletics.models.entities

import androidx.room.Entity
import com.kamilkulka.randletics.models.EquipmentType
import java.util.*

@Entity(primaryKeys = ["exerciseID","equipmentType"])
data class ExerciseEquipmentCrossRef(
    val exerciseID: UUID,
    val equipmentType: EquipmentType
)
