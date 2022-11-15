package com.kamilkulka.randletics.data

import androidx.room.*
import com.kamilkulka.randletics.models.entities.*
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

    @Query("SELECT COUNT(*) FROM equipment_table")
    fun getEquipmentsNumber(): Flow<Int>

    @Query("SELECT * FROM workouts_table WHERE workoutId=:id")
    fun getWorkoutById(id: String): Flow<Workout>

    @Query("SELECT * FROM exercise_table WHERE exerciseId=:id")
    fun getExerciseById(id: String): Flow<Exercise>

    @Query("SELECT * FROM equipment_table WHERE equipmentId=:equipmentId")
    fun getEquipmentByType(equipmentId: String): Flow<Equipment>

    @Transaction
    @Query("SELECT * FROM workouts_table")
    fun getWorkoutWithEquipments(): Flow<List<WorkoutWithEquipment>>

    @Transaction
    @Query("SELECT * FROM workouts_table WHERE workoutId=:id")
    fun getWorkoutWithEquipmentsByWorkoutId(id: String): Flow<WorkoutWithEquipment>

    @Transaction
    @Query("SELECT * FROM equipment_table")
    fun getEquipmentWithWorkouts(): Flow<List<EquipmentWithWorkout>>

    @Transaction
    @Query("SELECT * FROM exercise_table")
    fun getExerciseWithWorkouts(): List<ExerciseWithWorkout>

    @Transaction
    @Query("SELECT * FROM workouts_table")
    fun getWorkoutsWithExercises(): Flow<List<WorkoutWithExercise>>

    @Transaction
    @Query("SELECT * FROM workouts_table WHERE workoutId=:id")
    fun getWorkoutWithExercisesByWorkoutId(id: String): Flow<WorkoutWithExercise>

    @Transaction
    @Query("SELECT * FROM equipment_table")
    fun getEquipmentWithExercises(): Flow<List<EquipmentWithExercise>>

    @Transaction
    @Query("SELECT * FROM exercise_table")
    fun getExerciseWithEquipments(): Flow<List<ExerciseWithEquipment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseEquipmentCrossRef(crossRef: ExerciseEquipmentCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutEquipmentCrossRef(crossRef: WorkoutEquipmentCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExerciseCrossRef(crossRef: WorkoutExerciseCrossRef)

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

    @Delete
    suspend fun deleteExerciseEquipmentCrossRef(crossRef: ExerciseEquipmentCrossRef)

    @Delete
    suspend fun deleteWorkoutEquipmentCrossRef(crossRef: WorkoutEquipmentCrossRef)

    @Delete
    suspend fun deleteWorkoutExerciseCrossRef(crossRef: WorkoutExerciseCrossRef)
}