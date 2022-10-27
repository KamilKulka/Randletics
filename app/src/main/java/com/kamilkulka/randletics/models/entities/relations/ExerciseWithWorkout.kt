package com.kamilkulka.randletics.models.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.WorkoutExerciseCrossRef

data class ExerciseWithWorkout(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "workoutId",
        associateBy = Junction(WorkoutExerciseCrossRef::class)
    )
    val workouts: List<Workout>
)
