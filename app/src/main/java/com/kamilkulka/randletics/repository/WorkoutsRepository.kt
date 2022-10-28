package com.kamilkulka.randletics.repository

import com.kamilkulka.randletics.data.WorkoutDatabaseDao
import com.kamilkulka.randletics.models.entities.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WorkoutsRepository @Inject constructor(private val workoutDatabaseDao: WorkoutDatabaseDao){
    fun getAll(): Flow<List<Workout>> = workoutDatabaseDao.getWorkouts().flowOn(Dispatchers.IO).conflate()
    suspend fun getWorkoutById(id: String): Workout = workoutDatabaseDao.getWorkoutById(id)
    suspend fun insertWorkout(workout: Workout) = workoutDatabaseDao.insert(workout)
    suspend fun updateWorkout(workout: Workout) = workoutDatabaseDao.update(workout)
    suspend fun deleteAllWorkouts() = workoutDatabaseDao.deleteAllWorkouts()
    suspend fun deleteWorkout(workout: Workout) = workoutDatabaseDao.deleteWorkout(workout)
}