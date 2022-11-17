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
import kotlinx.coroutines.Job
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

    private val _workout = MutableStateFlow<Workout?>(null)
    val workout = _workout.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(
            emptyList()
    )
    val exercises = _exercises.asStateFlow()

    private val _equipments = MutableStateFlow<List<Equipment>>(emptyList())
    val equipments = _equipments.asStateFlow()

    private val _exercisesDropdown = MutableStateFlow(false)
    val exercisesDropdown = _exercisesDropdown.asStateFlow()

    private val _deletePopUp = MutableStateFlow(false)
    val deletePopUp = _deletePopUp.asStateFlow()

    private var job1: Job? = null
    private var job2: Job? = null
    private var job3: Job? = null



    companion object {

        const val TAG = "PreWorkoutViewModel"
    }

    init {

        savedStateHandle.get<String>("workoutId").let { workoutId ->
            if (!workoutId.isNullOrEmpty()) {
                job1 = viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutById(workoutId).distinctUntilChanged()
                        .collect { workout ->
                            _workout.value = workout
                        }
                    job1 = null
                }
                job2 = viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutWithExerciseByWorkoutId(workoutId).distinctUntilChanged()
                        .collect { workoutWithExercise ->
                            _exercises.value = workoutWithExercise.exercises
                        }
                    job2 = null
                }
                job3 = viewModelScope.launch(Dispatchers.IO) {
                    workoutsRepository.getWorkoutWithEquipmentsByWorkoutId(workoutId).distinctUntilChanged()
                        .collect { workoutWithEquipments ->
                            _equipments.value = workoutWithEquipments.equipments
                        }
                    job3 = null
                }
            }
        }
    }

    fun setDeletePopUp(isVisible: Boolean) {
        _deletePopUp.value = isVisible
    }

    fun setExercisesDropdown() {
        _exercisesDropdown.value = !_exercisesDropdown.value
    }

    fun countExercisesOf(): Int {
        if (_exercises.value.isEmpty()) return 0

        return _exercises.value.size
    }

    fun getFormattedDifficulty(): String {
        if (_workout.value!=null){
            val difficulty =
                _workout.value!!.difficulty
            return difficulty.toString().substring(0, 1) + difficulty.toString().substring(1)
                .lowercase()
        }
        return "-"
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

    fun deleteWorkout(){
        if (_workout.value!=null){
            val idToDelete = _workout.value!!.workoutId.toString()
            job1?.cancel()
            job2?.cancel()
            job3?.cancel()

        Log.d(TAG,"ViewModelScope Cancelled")

            _workout.value=null
            _exercises.value= emptyList()
            _equipments.value= emptyList()

            viewModelScope.launch(Dispatchers.Main) {
                workoutsRepository.deleteWorkoutEquipmentById(idToDelete)
                Log.d(TAG,"Workout Equipment deleted!")
            }
            viewModelScope.launch(Dispatchers.Main) {
                workoutsRepository.deleteWorkoutExerciseById(idToDelete)
                Log.d(TAG,"Workout Exercises deleted!")
            }
            viewModelScope.launch(Dispatchers.Main) {
                workoutsRepository.deleteWorkoutById(idToDelete)
                Log.d(TAG,"Workout deleted!")
            }
        }
    }
}