package com.kamilkulka.randletics.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kamilkulka.randletics.models.entities.*
import com.kamilkulka.randletics.utils.*

@Database(
    entities = [
        Equipment::class,
        Exercise::class,
        ExerciseEquipmentCrossRef::class,
        Workout::class,
        WorkoutEquipmentCrossRef::class,
        WorkoutExerciseCrossRef::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(
    UUIDConverter::class,
    MuscleTypeConverter::class,
    DifficultyConverter::class,
    EquipmentTypeConverter::class
)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutsDao(): WorkoutDatabaseDao
}