package com.kamilkulka.randletics.models


import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kamilkulka.randletics.ui.theme.BlueGray
import com.kamilkulka.randletics.ui.theme.PinkRosewater
import com.kamilkulka.randletics.ui.theme.PurpleOrchid
import java.util.*

@Entity(tableName = "workouts_table")
data class Workout(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "workout_title")
    val title: String = "My Workout",
    @ColumnInfo(name = "workout_exercises_id")
    val listOfExercisesID: List<UUID> = emptyList(),
    @ColumnInfo(name = "workout_difficulty")
    val difficulty: Difficulty = Difficulty.MEDIUM,
    )
