package com.kamilkulka.randletics.models.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.WorkoutEquipmentCrossRef

data class EquipmentWithWorkout(
    @Embedded val equipment: Equipment,
    @Relation(
        parentColumn = "equipmentId",
        entityColumn = "workoutId",
        associateBy = Junction(WorkoutEquipmentCrossRef::class)
    )
    val workouts: List<Workout>
)
