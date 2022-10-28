package com.kamilkulka.randletics.models.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.WorkoutEquipmentCrossRef

data class WorkoutWithEquipment(
    @Embedded
    val workout: Workout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "equipmentId",
        associateBy = Junction(WorkoutEquipmentCrossRef::class)
    )
    val equipments: List<Equipment>
)
