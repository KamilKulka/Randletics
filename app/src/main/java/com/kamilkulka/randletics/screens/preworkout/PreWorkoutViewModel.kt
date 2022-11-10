package com.kamilkulka.randletics.screens.preworkout

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.relations.WorkoutWithEquipment
import com.kamilkulka.randletics.models.entities.relations.WorkoutWithExercise
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PreWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {

    private val _workoutsList = MutableStateFlow<List<Workout>>(emptyList())
    private val _workoutsWithExercises = MutableStateFlow<List<WorkoutWithExercise>>(emptyList())
    private val _workoutWithEquipments = MutableStateFlow<List<WorkoutWithEquipment>>(emptyList())
    private val _exercisesDropdown = MutableStateFlow(false)
    val exercisesDropdown=_exercisesDropdown.asStateFlow()

    companion object {
        const val TAG = "PreWorkoutViewModel"
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllWorkouts().distinctUntilChanged().collect { listOfWorkouts ->
                if (listOfWorkouts.isEmpty()) {
                    Log.d(TAG, "List of workouts is empty")
                } else {
                    _workoutsList.value = listOfWorkouts
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllWorkoutsWithAllExercises().distinctUntilChanged()
                .collect() { listOfAllWorkoutsWithExercises ->
                    if (listOfAllWorkoutsWithExercises.isEmpty()) {
                        Log.d(TAG, "ListOfAllWorkoutsWithExercises is empty")
                    } else {
                        _workoutsWithExercises.value = listOfAllWorkoutsWithExercises
                    }
                }
        }
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllWorkoutsWithEquipments().distinctUntilChanged()
                .collect() { listOfAllWorkoutsWithEquipments ->
                    if (listOfAllWorkoutsWithEquipments.isEmpty()) {
                        Log.d(TAG, "ListOfAllWorkoutsWithEquipments is empty")
                    } else {
                        _workoutWithEquipments.value = listOfAllWorkoutsWithEquipments
                    }
                }
        }
    }

    fun setExercisesDropdown(){
        _exercisesDropdown.value= !_exercisesDropdown.value
    }

    fun getWorkoutFromUUID(uuid: UUID): Workout {
        return _workoutsList.value.first { workout -> workout.workoutId == uuid }
    }

    fun countExercisesOf(uuid: UUID): Int {
        return _workoutsWithExercises.value.first { workoutWithExercise ->
            workoutWithExercise.workout.workoutId == uuid
        }.exercises.size
    }

    fun getExercisesList(uuid: UUID):List<Exercise>{
        return _workoutsWithExercises.value.first{ workoutWithExercise ->
            workoutWithExercise.workout.workoutId==uuid
        }.exercises
    }
    fun getDifficultyOf(uuid: UUID): Difficulty {
        return _workoutsList.value.first { workout -> workout.workoutId == uuid }.difficulty
    }

    fun getDifficultyStringOf(uuid: UUID): String {
        val difficulty =
            _workoutsList.value.first { workout -> workout.workoutId == uuid }.difficulty
        return difficulty.toString().substring(0, 1) + difficulty.toString().substring(1)
            .lowercase()
    }

    fun getEquipmentStringOf(uuid: UUID): String {
        var string = ""
        val listOfEquipment =
            _workoutWithEquipments.value.first { workoutWithEquipment ->
                workoutWithEquipment.workout.workoutId == uuid }.equipments

        if (listOfEquipment.size==0){
            return "No Equipment"
        }else{
            var index = 0
            while (index < listOfEquipment.size){
                if (index==listOfEquipment.size-1){
                    string +=listOfEquipment[index].equipmentName
                }else{
                    string = string+listOfEquipment[index].equipmentName + ", "
                }
                index++
            }
        }
            return string
    }
}