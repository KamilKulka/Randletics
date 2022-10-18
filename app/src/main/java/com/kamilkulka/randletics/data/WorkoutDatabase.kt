package com.kamilkulka.randletics.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kamilkulka.randletics.models.Workout
import com.kamilkulka.randletics.utils.ColorConverter
import com.kamilkulka.randletics.utils.DifficultyConverter
import com.kamilkulka.randletics.utils.ExercisesListConverter
import com.kamilkulka.randletics.utils.UUIDConverter

@Database(entities = [Workout::class], version =2, exportSchema = true)
@TypeConverters(UUIDConverter::class, ExercisesListConverter::class, ColorConverter::class, DifficultyConverter::class)
abstract class WorkoutDatabase: RoomDatabase() {
    abstract fun workoutsDao(): WorkoutDatabaseDao
}