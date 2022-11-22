package com.kamilkulka.randletics.repository

import com.kamilkulka.randletics.data.WorkoutDatabaseDao
import com.kamilkulka.randletics.models.entities.*
import com.kamilkulka.randletics.models.entities.relations.EquipmentWithExercise
import com.kamilkulka.randletics.models.entities.relations.ExerciseWithEquipment
import com.kamilkulka.randletics.models.entities.relations.WorkoutWithEquipment
import com.kamilkulka.randletics.models.entities.relations.WorkoutWithExercise
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
    fun getAllWorkoutsWithAllExercises(): Flow<List<WorkoutWithExercise>> = workoutDatabaseDao.getWorkoutsWithExercises().flowOn(Dispatchers.IO).conflate()
    fun getWorkoutWithExerciseByWorkoutId(id: String): Flow<WorkoutWithExercise> = workoutDatabaseDao.getWorkoutWithExercisesByWorkoutId(id).flowOn(Dispatchers.IO).conflate()
    fun getAllExercisesWithEquipments(): Flow<List<ExerciseWithEquipment>> = workoutDatabaseDao.getAllExerciseWithEquipments().flowOn(Dispatchers.IO).conflate()
    fun getExerciseWithEquipmentsByExerciseId(id: String): Flow<ExerciseWithEquipment> = workoutDatabaseDao.getExerciseWithEquipmentsByExerciseId(id).flowOn(Dispatchers.IO).conflate()
    fun getAllWorkoutsWithEquipments(): Flow<List<WorkoutWithEquipment>> = workoutDatabaseDao.getWorkoutWithEquipments().flowOn(Dispatchers.IO).conflate()
    fun getWorkoutWithEquipmentsByWorkoutId(id: String):Flow<WorkoutWithEquipment> =workoutDatabaseDao.getWorkoutWithEquipmentsByWorkoutId(id).flowOn(Dispatchers.IO).conflate()
    fun getWorkoutById(id: String): Flow<Workout> = workoutDatabaseDao.getWorkoutById(id).flowOn(Dispatchers.IO).conflate()
    fun getEquipmentsNumber(): Flow<Int> = workoutDatabaseDao.getEquipmentsNumber().flowOn(Dispatchers.IO).conflate()
    suspend fun insertWorkout(workout: Workout) = workoutDatabaseDao.insert(workout)
    suspend fun updateWorkout(workout: Workout) = workoutDatabaseDao.update(workout)
    suspend fun deleteAllWorkouts() = workoutDatabaseDao.deleteAllWorkouts()
    suspend fun deleteWorkout(workout: Workout) = workoutDatabaseDao.deleteWorkout(workout)
    suspend fun insertWorkoutExercise(crossRef: WorkoutExerciseCrossRef) = workoutDatabaseDao.insertWorkoutExerciseCrossRef(crossRef)
    suspend fun insertWorkoutEquipment(crossRef: WorkoutEquipmentCrossRef)= workoutDatabaseDao.insertWorkoutEquipmentCrossRef(crossRef)
    suspend fun deleteWorkoutById(id:String) = workoutDatabaseDao.deleteWorkoutById(id)
    suspend fun deleteWorkoutExerciseById(id:String) = workoutDatabaseDao.deleteWorkoutExerciseById(id)
    suspend fun deleteWorkoutEquipmentById(id:String) = workoutDatabaseDao.deleteWorkoutEquipmentById(id)
}