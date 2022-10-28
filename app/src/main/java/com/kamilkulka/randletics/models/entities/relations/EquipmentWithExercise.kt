package com.kamilkulka.randletics.models.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.ExerciseEquipmentCrossRef

data class EquipmentWithExercise(
    @Embedded val equipment: Equipment,
    @Relation(
        parentColumn = "equipmentId",
        entityColumn = "exerciseId",
        associateBy = Junction(ExerciseEquipmentCrossRef::class)
    )
    val exercises: List<Exercise>
)
