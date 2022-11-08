package com.kamilkulka.randletics.repository

import com.kamilkulka.randletics.data.WorkoutDatabaseDao
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.relations.EquipmentWithExercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WorkoutsRepository @Inject constructor(private val workoutDatabaseDao: WorkoutDatabaseDao){
    fun getAllWorkouts(): Flow<List<Workout>> = workoutDatabaseDao.getWorkouts().flowOn(Dispatchers.IO).conflate()
    fun getAllEquipments(): Flow<List<Equipment>> = workoutDatabaseDao.getEquipments().flowOn(Dispatchers.IO).conflate()
    fun getAllExercises(): Flow<List<Exercise>> = workoutDatabaseDao.getExercises().flowOn(Dispatchers.IO).conflate()
    fun getAllEquipmentsWithExercises(): Flow<List<EquipmentWithExercise>> = workoutDatabaseDao.getEquipmentWithExercises().flowOn(Dispatchers.IO).conflate()
    suspend fun getWorkoutById(id: String): Workout = workoutDatabaseDao.getWorkoutById(id)
    suspend fun insertWorkout(workout: Workout) = workoutDatabaseDao.insert(workout)
    suspend fun updateWorkout(workout: Workout) = workoutDatabaseDao.update(workout)
    suspend fun deleteAllWorkouts() = workoutDatabaseDao.deleteAllWorkouts()
    suspend fun deleteWorkout(workout: Workout) = workoutDatabaseDao.deleteWorkout(workout)
    suspend fun getEquipmentsNumber(): Int = workoutDatabaseDao.getEquipmentsNumber()
}