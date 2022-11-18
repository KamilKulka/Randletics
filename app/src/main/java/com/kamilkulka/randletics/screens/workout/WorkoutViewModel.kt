package com.kamilkulka.randletics.screens.workout

import android.os.CountDownTimer
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
class WorkoutViewModel @Inject constructor(
    private val workoutsRepository: WorkoutsRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    companion object {
        const val TAG = "WorkoutViewModel"
        const val SEC_SPAN = 45
    }

    private val _workout = MutableStateFlow<Workout?>(null)
    val workout = _workout.asStateFlow()

    private val _equipments = MutableStateFlow<List<Equipment>>(emptyList())
    val equipments = _equipments.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises = _exercises.asStateFlow()

    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft = _timeLeft.asStateFlow()

    private val _progressBar = MutableStateFlow(1f)
    val progressBar = _progressBar.asStateFlow()

    private val counter = object : CountDownTimer(SEC_SPAN * 1000L, 1) {
        override fun onTick(millisUntilFinished: Long) {
            _timeLeft.value = millisUntilFinished / 1000
            _progressBar.value = millisUntilFinished.toFloat() / (SEC_SPAN * 1000).toFloat()
        }

        override fun onFinish() {

        }
    }

    init {
        savedStateHandle.get<String>("workoutId").let { workoutId ->
            if (workoutId.isNullOrEmpty() || workoutId == "null") {
                Log.d(TAG, "WorkoutId delivered by SavedStateHandle is null")
            } else {
                viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutById(workoutId).distinctUntilChanged()
                        .collect { workout ->
                            _workout.value = workout
                            Log.d(TAG, "Workout init")
                        }
                }
                viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutWithExerciseByWorkoutId(workoutId).distinctUntilChanged()
                        .collect { workoutWithExercises ->
                            _exercises.value = workoutWithExercises.exercises
                            Log.d(TAG, "Exercises init")
                        }
                }
                viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutWithEquipmentsByWorkoutId(workoutId).distinctUntilChanged()
                        .collect { workoutWithEquipments ->
                            _equipments.value = workoutWithEquipments.equipments
                            Log.d(TAG, "Equipments init")
                        }
                }
            }
        }
    }


    fun startCounter() {
        counter.start()
    }
}