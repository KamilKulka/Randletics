package com.kamilkulka.randletics.screens.newworkout

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
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
                        for (i in 1..listOfEquipments.size) equipmentsState.add(MutableStateFlow(true))
                        Log.d("Equipment", "Init")
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

    fun createWorkout(){
        var workoutDifficulty = Difficulty.MEDIUM
        if(_difficultySlider.value<0.33f){
            workoutDifficulty = Difficulty.EASY
        }else if(_difficultySlider.value>0.67){
            workoutDifficulty =Difficulty.HARD
        }
        viewModelScope.launch {
            workoutsRepository.insertWorkout(workout = Workout(title = _workoutTitle.value, difficulty = workoutDifficulty))
        }
    }
}