package com.kamilkulka.randletics.screens.newworkout

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.relations.EquipmentWithExercise
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    private val _exercisesList = MutableStateFlow<List<Exercise>>(emptyList())
    private val _equipmentsWithExercises =
        MutableStateFlow<List<EquipmentWithExercise>>(emptyList())
    private val _workoutTitle = MutableStateFlow("")
    var workoutTitle = _workoutTitle.asStateFlow()

    private val _difficultySlider = MutableStateFlow(0f)
    var difficultySlider = _difficultySlider.asStateFlow()

    private val _equipmentList = MutableStateFlow<List<Equipment>>(emptyList())
    val equipmentList = _equipmentList.asStateFlow()

    val equipmentsState = mutableListOf<MutableStateFlow<Boolean>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllEquipments().distinctUntilChanged()
                .collect { listOfEquipments ->
                    if (listOfEquipments.isEmpty()) {
                        Log.d("Empty", "Equipment list is empty!")
                    } else {
                        _equipmentList.value = listOfEquipments
                        for (i in 1..listOfEquipments.size) equipmentsState.add(
                            MutableStateFlow(
                                true
                            )
                        )
                        Log.d("Equipment", "Init")
                    }
                }
        }
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllExercises().distinctUntilChanged()
                .collect { listOfExercises ->
                    if (listOfExercises.isEmpty()) {
                        Log.d("Empty", "Exercises list is empty!")
                    } else {
                        _exercisesList.value = listOfExercises
                        Log.d("Exercises", "Init")
                    }
                }
        }
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllEquipmentsWithExercises().distinctUntilChanged()
                .collect() { listOfEquipmentsWithExercises ->
                    if (listOfEquipmentsWithExercises.isEmpty()) {
                        Log.d("Empty", "EquipmentsWithExercises list is empty!")
                    } else {
                        _equipmentsWithExercises.value = listOfEquipmentsWithExercises
                        Log.d("EquipmentsWithExercises", "Init")
                    }
                }
        }
    }

    fun setWorkoutTitle(workoutTitle: String) {
        _workoutTitle.value = workoutTitle
    }

    fun setDifficultySlider(sliderValue: Float) {
        _difficultySlider.value = sliderValue
    }

    fun createWorkout() {
        val exercisesList = mutableListOf<Exercise>()
        val workoutDifficulty = if (_difficultySlider.value < 0.33f) {
            Difficulty.EASY
        } else if (_difficultySlider.value > 0.67) {
            Difficulty.HARD
        } else Difficulty.MEDIUM

        for (equipment in _equipmentList.value) {
            Log.d("Equipment", ": ${equipment.equipmentName}")
        }

        when (workoutDifficulty) {
            Difficulty.EASY -> {

            }
            Difficulty.MEDIUM -> {

            }
            Difficulty.HARD -> {

            }
        }

//        viewModelScope.launch {
//            workoutsRepository.insertWorkout(
//                workout = Workout(
//                    title = _workoutTitle.value,
//                    difficulty = workoutDifficulty
//                )
//            )
//        }
    }

    private fun addNumOfExercisesToCollection(
        collection: MutableList<Exercise>,
        quantity: Int = 1,
        difficulty: Difficulty = Difficulty.HARD
    ) {
        if (quantity > 0) {
            for (i in 0..quantity) {
                when (difficulty) {
                    Difficulty.EASY -> {

                    }
                    Difficulty.MEDIUM -> {

                    }
                    Difficulty.HARD -> {

                    }
                }
            }
        }
    }
}