package com.kamilkulka.randletics.screens.exercises

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.EquipmentType
import com.kamilkulka.randletics.models.Muscle
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository):ViewModel() {
    private val _difficultyDropDown = MutableStateFlow(false)
    val difficultyDropDown = _difficultyDropDown.asStateFlow()

    private val _muscleCategoryDropDown = MutableStateFlow(false)
    val muscleCategoryDropDown = _muscleCategoryDropDown.asStateFlow()

    private val _listOfExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val listOfExercises = _listOfExercises.asStateFlow()

    private val _difficultyFilterSelected = MutableStateFlow<Difficulty?>(null)
//    val difficultyFilterSelected = _difficultyFilterSelected.asStateFlow()

    private val _muscleFilterSelected = MutableStateFlow<Muscle?>(null)
//    val muscleFilterSelected = _muscleFilterSelected.asStateFlow()

    val difficulties = Difficulty.values()
    val muscleType = Muscle.values()

    init {
        viewModelScope.launch {
            workoutsRepository.getAllExercises().distinctUntilChanged().collect { listOfExercises ->
                if (listOfExercises.isNotEmpty()){
                    _listOfExercises.value=listOfExercises
                }
            }
        }
    }

    fun getDifficultyFilterSelected():String{
        return when(_difficultyFilterSelected.value){
            Difficulty.HARD -> "Hard"
            Difficulty.MEDIUM -> "Medium"
            Difficulty.EASY -> "Easy"
            null -> "All"
        }
    }

    fun getMuscleFilterSelected():String{
        return when(_muscleFilterSelected.value){
            Muscle.CORE -> "Core"
            Muscle.LEGS -> "Legs"
            Muscle.TRICEPS -> "Triceps"
            Muscle.BICEPS -> "Biceps"
            Muscle.SHOULDERS -> "Shoulders"
            Muscle.CHEST -> "Chest"
            Muscle.BACK -> "Back"
            null -> "All"
        }
    }

    fun setDifficultyFilter(difficulty: Difficulty?){
        _difficultyFilterSelected.value = difficulty
    }

    fun setMuscleFilter(muscle: Muscle?){
        _muscleFilterSelected.value = muscle
    }

    fun setDifficultyDropDown(){ _difficultyDropDown.value = !_difficultyDropDown.value }

    fun setMuscleCategoryDropDown(){ _muscleCategoryDropDown.value = !_muscleCategoryDropDown.value }

}