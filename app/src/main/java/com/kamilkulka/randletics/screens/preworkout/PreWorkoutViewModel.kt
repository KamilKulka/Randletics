package com.kamilkulka.randletics.screens.preworkout

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreWorkoutViewModel @Inject constructor(
    private val workoutsRepository: WorkoutsRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _workout = MutableStateFlow(Workout(title = "Empty Workout"))
    val workout = _workout.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(
            emptyList()
    )
    val exercises = _exercises.asStateFlow()

    private val _equipments = MutableStateFlow<List<Equipment>>(emptyList())
    val equipments = _equipments.asStateFlow()

    private val _exercisesDropdown = MutableStateFlow(false)
    val exercisesDropdown = _exercisesDropdown.asStateFlow()

    companion object {
        const val TAG = "PreWorkoutViewModel"
    }

    init {

        savedStateHandle.get<String>("workoutId").let { workoutId ->
            if (workoutId.isNullOrEmpty()) {
                Log.d(TAG, "Err: No workoutId")
            } else {
                viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutById(workoutId).distinctUntilChanged()
                        .collect { workout ->
                            _workout.value = workout
                            Log.d(TAG, "Workout name: ${_workout.value.title}")
                        }
                }
                viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutWithExerciseByWorkoutId(workoutId).distinctUntilChanged()
                        .collect { workoutWithExercise ->
                            _exercises.value = workoutWithExercise.exercises
                        }
                }
                viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutWithEquipmentsByWorkoutId(workoutId).distinctUntilChanged()
                        .collect { workoutWithEquipments ->
                            _equipments.value = workoutWithEquipments.equipments
                        }
                }
            }
        }
    }

    fun setExercisesDropdown() {
        _exercisesDropdown.value = !_exercisesDropdown.value
    }

    fun countExercisesOf(): Int {
        return exercises.value.size
    }

    fun getFormattedDifficulty(): String {
        val difficulty =
            _workout.value.difficulty
        return difficulty.toString().substring(0, 1) + difficulty.toString().substring(1)
            .lowercase()
    }

    fun getAllEquipmentsString(): String {
        var string = ""
        val listOfEquipment = _equipments.value

        if (listOfEquipment.isEmpty()) {
            return "No Equipment"
        } else {
            var index = 0
            while (index < listOfEquipment.size) {
                if (index == listOfEquipment.size - 1) {
                    string += listOfEquipment[index].equipmentName
                } else {
                    string = string + listOfEquipment[index].equipmentName + ", "
                }
                index++
            }
        }
        return string
    }
}