package com.kamilkulka.randletics.data

import androidx.room.*
import com.kamilkulka.randletics.models.EquipmentType
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.relations.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDatabaseDao {

    @Query("SELECT * FROM workouts_table")
    fun getWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM exercise_table")
    fun getExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM equipment_table")
    fun getEquipments(): Flow<List<Equipment>>

    @Query("SELECT * FROM workouts_table WHERE workoutId=:id")
    suspend fun getWorkoutById(id: String): Workout

    @Query("SELECT * FROM exercise_table WHERE exerciseId=:id")
    suspend fun getExerciseById(id: String): Exercise

    @Query("SELECT * FROM equipment_table WHERE equipmentType=:equipmentType")
    suspend fun getEquipmentByType(equipmentType: EquipmentType): EquipmentType
//TODO: flow for querries below
    @Transaction
    @Query("SELECT * FROM workouts_table")
    fun getWorkoutWithEquipments(): List<WorkoutWithEquipment>

    @Transaction
    @Query("SELECT * FROM equipment_table")
    fun getEquipmentWithWorkouts(): List<EquipmentWithWorkout>

    @Transaction
    @Query("SELECT * FROM exercise_table")
    fun getExerciseWithWorkouts(): List<ExerciseWithWorkout>

    @Transaction
    @Query("SELECT * FROM workouts_table")
    fun getWorkoutWithExercises(): List<WorkoutWithExercise>

    @Transaction
    @Query("SELECT * FROM equipment_table")
    fun getEquipmentWithExercises(): List<EquipmentWithExercise>

    @Transaction
    @Query("SELECT * FROM exercise_table")
    fun getExerciseWithEquipments(): List<ExerciseWithEquipment>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(workout: Workout)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(workout: Workout)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(exercise: Exercise)

    @Query("DELETE from workouts_table")
    suspend fun deleteAllWorkouts()

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
//
//    @Delete
//    suspend fun deleteExercise(exercise: Exercise)
//
//    @Delete
//    suspend fun deleteExercise(exercise: Exercise)
//
//    @Delete
//    suspend fun deleteExercise(exercise: Exercise)
}