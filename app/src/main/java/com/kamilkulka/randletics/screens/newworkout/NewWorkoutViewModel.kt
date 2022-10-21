package com.kamilkulka.randletics.screens.newworkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    private val _workoutTitle = MutableStateFlow("")
    var workoutTitle = _workoutTitle.asStateFlow()

    fun setWorkoutTitle(workoutTitle: String){
        _workoutTitle.value = workoutTitle
    }

    private val _difficultySlider = MutableStateFlow(0f)
    var difficultySlider = _difficultySlider.asStateFlow()

    fun setDifficultySlider(sliderValue: Float){
        _difficultySlider.value = sliderValue
    }
}