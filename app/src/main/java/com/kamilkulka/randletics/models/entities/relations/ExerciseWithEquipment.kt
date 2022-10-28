package com.kamilkulka.randletics.models.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.ExerciseEquipmentCrossRef

data class ExerciseWithEquipment(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "equipmentId",
        associateBy = Junction(ExerciseEquipmentCrossRef::class)
    )
    val equipments: List<Equipment>
)
