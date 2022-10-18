package com.kamilkulka.randletics.data

import androidx.room.*
import com.kamilkulka.randletics.models.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDatabaseDao {

    @Query("SELECT * FROM workouts_table")
    fun getWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts_table WHERE id=:id")
    suspend fun getWorkoutById(id: String): Workout

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(workout: Workout)

    @Query("DELETE from workouts_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteWorkout(workout: Workout)
}