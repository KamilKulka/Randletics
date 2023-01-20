package com.kamilkulka.randletics.screens.exercises

import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.Muscle
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    private val _difficultyDropDown = MutableStateFlow(false)
    val difficultyDropDown = _difficultyDropDown.asStateFlow()

    private val _muscleCategoryDropDown = MutableStateFlow(false)
    val muscleCategoryDropDown = _muscleCategoryDropDown.asStateFlow()

    private val _listOfExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val listOfExercises = _listOfExercises.asStateFlow()

    private val _difficultyFilterSelected = MutableStateFlow<Difficulty?>(null)
    val difficultyFilterSelected = _difficultyFilterSelected.asStateFlow()

    private val _muscleFilterSelected = MutableStateFlow<Muscle?>(null)
    val muscleFilterSelected = _muscleFilterSelected.asStateFlow()

    private val _listOfFilteredExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val listOfFilteredExercises = _listOfFilteredExercises.asStateFlow()

    val difficulties = Difficulty.values()
    val muscleType = Muscle.values()

    init {
        viewModelScope.launch {
            workoutsRepository.getAllExercises().distinctUntilChanged().collect { listOfExercises ->
                if (listOfExercises.isNotEmpty()) {
                    _listOfExercises.value = listOfExercises
                    _listOfFilteredExercises.value = listOfExercises
                }
            }
        }
    }

    private fun refreshExercisesList() {
        if (_difficultyFilterSelected.value != null && _muscleFilterSelected.value != null) {
            _listOfFilteredExercises.value =
                _listOfExercises.value.filter { it.difficulty == _difficultyFilterSelected.value && it.muscleGroup == _muscleFilterSelected.value }
        } else if (_difficultyFilterSelected.value == null && _muscleFilterSelected.value != null) {
            _listOfFilteredExercises.value =
                _listOfExercises.value.filter { it.muscleGroup == _muscleFilterSelected.value }
        } else if (_difficultyFilterSelected.value != null && _muscleFilterSelected.value == null) {
            _listOfFilteredExercises.value =
                _listOfExercises.value.filter { it.difficulty == _difficultyFilterSelected.value }
        } else {
            _listOfFilteredExercises.value = _listOfExercises.value
        }
    }

    fun setDifficultyFilter(difficulty: Difficulty?) {
        _difficultyFilterSelected.value = difficulty
        refreshExercisesList()
    }

    fun setMuscleFilter(muscle: Muscle?) {
        _muscleFilterSelected.value = muscle
        refreshExercisesList()
    }

    fun setDifficultyDropDown() {
        _difficultyDropDown.value = !_difficultyDropDown.value
    }

    fun setMuscleCategoryDropDown() {
        _muscleCategoryDropDown.value = !_muscleCategoryDropDown.value
    }

    fun toFormattedString(string: String): String {
        if (string.isNotEmpty()) {
            return string.substring(0) + string.substring(1).lowercase()
        }
        return string
    }
}