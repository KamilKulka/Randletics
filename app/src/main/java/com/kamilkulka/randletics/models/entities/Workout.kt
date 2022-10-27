package com.kamilkulka.randletics.models.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kamilkulka.randletics.models.Difficulty
import java.util.*

@Entity(tableName = "workouts_table")
data class Workout(
    @PrimaryKey
    val workoutId: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "workout_title")
    val title: String = "My Workout",
    @ColumnInfo(name = "workout_exercises_id")
    val listOfExercisesID: List<UUID> = emptyList(),
    @ColumnInfo(name = "workout_difficulty")
    val difficulty: Difficulty = Difficulty.MEDIUM,
    @ColumnInfo(name = "workout_equipment")
    val equipmentList: MutableList<Equipment> = mutableListOf<Equipment>()
    )
