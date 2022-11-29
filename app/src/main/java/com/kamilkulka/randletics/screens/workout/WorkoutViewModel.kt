package com.kamilkulka.randletics.screens.workout

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.material.DrawerValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.models.entities.relations.ExerciseWithEquipment
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

    private val _exercisesWithEquipments =
        MutableStateFlow<List<ExerciseWithEquipment>>(emptyList())

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises = _exercises.asStateFlow()

    private val _restScreen = MutableStateFlow(false)
    val restScreen = _restScreen.asStateFlow()

    private val _exitPopup = MutableStateFlow(false)
    val exitPopup = _exitPopup.asStateFlow()

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
            changeRestScreen()
        }

    }

    private var _currentExercise = MutableStateFlow(1)
    private var _currentSeries = MutableStateFlow(1)

    init {
        savedStateHandle.get<String>("workoutId").let { workoutId ->
            if (workoutId.isNullOrEmpty() || workoutId == "null") {
                Log.d(TAG, "WorkoutId delivered by SavedStateHandle is null")
            } else {
                getWorkout(workoutId)
                getWorkoutWithExercise(workoutId)
                getWorkoutWithEquipments(workoutId)
                getAllExercisesWithEquipments()
            }
        }
    }

    private fun getWorkout(workoutId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getWorkoutById(workoutId).distinctUntilChanged()
                .collect { workout ->
                    _workout.value = workout
                    Log.d(TAG, "Workout init")
                }
        }
    }

    private fun getWorkoutWithExercise(workoutId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getWorkoutWithExerciseByWorkoutId(workoutId)
                .distinctUntilChanged()
                .collect { workoutWithExercises ->
                    _exercises.value = workoutWithExercises.exercises
                    Log.d(TAG, "Exercises init")
                }
        }
    }

    private fun getWorkoutWithEquipments(workoutId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getWorkoutWithEquipmentsByWorkoutId(workoutId)
                .distinctUntilChanged()
                .collect { workoutWithEquipments ->
                    _equipments.value = workoutWithEquipments.equipments
                    Log.d(TAG, "Equipments init")
                }
        }
    }

    private fun getAllExercisesWithEquipments() {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllExercisesWithEquipments().distinctUntilChanged()
                .collect() { exercisesWithEquipments ->
                    _exercisesWithEquipments.value = exercisesWithEquipments
                }
        }
    }

    fun openTutorial(context: Context){
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getTutorialUri()))
        context.startActivity(urlIntent)
    }

    fun setExitPopup() {
        _exitPopup.value = !_exitPopup.value
    }

    fun getExercisesLeft(): Int {
        return _exercises.value.size - _currentExercise.value
    }

    fun getExerciseTitle(): String {
        if (_exercises.value.isNotEmpty()) {
            return _exercises.value[_currentExercise.value - 1].name
        }
        return ""
    }

    fun getTutorialUri(): String {
        if (_exercises.value.isNotEmpty()) {
            Log.d(TAG,_exercises.value[_currentExercise.value - 1].videoTutorial)
            return _exercises.value[_currentExercise.value - 1].videoTutorial
        }
        return ""
    }

    fun getReps(): Int {
        if (_exercises.value.isNotEmpty()) {
            return when (_currentSeries.value) {
                2 -> (_exercises.value[_currentExercise.value - 1].defaultRepeats * 5) / 6
                3 -> (_exercises.value[_currentExercise.value - 1].defaultRepeats * 3) / 4
                else -> _exercises.value[_currentExercise.value - 1].defaultRepeats
            }
        }
        return 0
    }

    fun getSeries(): Int {
        return _currentSeries.value
    }

    fun getEquipmentForExercise(): String {
        if (_exercises.value.isNotEmpty() && _currentExercise.value > 0 &&
            _exercisesWithEquipments.value.any {
                it.exercise.exerciseId == _exercises.value[_currentExercise.value - 1].exerciseId }) {

            val currentExerciseEquipments = _exercisesWithEquipments.value
                .first {
                    it.exercise.exerciseId == _exercises.value[_currentExercise.value - 1].exerciseId
                }.equipments
            if (currentExerciseEquipments.isNotEmpty()) {
                var index = 0
                var exercisesString = ""
                while (index < currentExerciseEquipments.size) {
                    if (index == currentExerciseEquipments.size - 1) {
                        exercisesString += currentExerciseEquipments[index].equipmentName
                    } else {
                        exercisesString =
                            exercisesString + currentExerciseEquipments[index].equipmentName + ", "
                    }
                    index++
                }
                return exercisesString
            }
        }
        return "No equipment"
    }

    fun getNextExerciseTitle(): String {
        if (_exercises.value.size > _currentExercise.value) return _exercises.value[_currentExercise.value].name
        return "End of workout"
    }

    private fun changeRestScreen() {
        _restScreen.value = !_restScreen.value
    }

    fun skipCounter() {
        _restScreen.value = false
        counter.cancel()
    }

    private fun startCounter() {
        counter.start()
    }

    fun complete() {
        if (_currentSeries.value == 3) {
            _currentSeries.value = 1
        } else {
            _currentSeries.value++

        }
        if (_currentSeries.value == 1) {
            _currentExercise.value++
        }

        changeRestScreen()
        startCounter()
    }
}