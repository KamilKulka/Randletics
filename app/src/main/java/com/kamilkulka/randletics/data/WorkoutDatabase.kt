package com.kamilkulka.randletics.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.utils.DifficultyConverter
import com.kamilkulka.randletics.utils.EquipmentListConverter
import com.kamilkulka.randletics.utils.ExercisesListConverter
import com.kamilkulka.randletics.utils.UUIDConverter

@Database(entities = [Workout::class], version = 2, exportSchema = false)
@TypeConverters(
    UUIDConverter::class,
    ExercisesListConverter::class,
    DifficultyConverter::class,
    EquipmentListConverter::class
)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutsDao(): WorkoutDatabaseDao
}