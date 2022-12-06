package com.kamilkulka.randletics.screens.newworkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Difficulty
import com.kamilkulka.randletics.models.Muscle
import com.kamilkulka.randletics.models.entities.*
import com.kamilkulka.randletics.models.entities.relations.EquipmentWithExercise
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    companion object {
        const val TAG = "NewWorkoutViewModel"
    }

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
                        Log.d(TAG, "Equipment list is empty!")
                    } else {
                        _equipmentList.value = listOfEquipments
                        for (i in 1..listOfEquipments.size) equipmentsState.add(
                            MutableStateFlow(
                                true
                            )
                        )
                    }
                }
        }
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllExercises().distinctUntilChanged()
                .collect { listOfExercises ->
                    if (listOfExercises.isEmpty()) {
                        Log.d(TAG, "Exercises list is empty!")
                    } else {
                        _exercisesList.value = listOfExercises
                    }
                }
        }
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllEquipmentsWithExercises().distinctUntilChanged()
                .collect() { listOfEquipmentsWithExercises ->
                    if (listOfEquipmentsWithExercises.isEmpty()) {
                        Log.d(TAG, "EquipmentsWithExercises list is empty!")
                    } else {
                        _equipmentsWithExercises.value = listOfEquipmentsWithExercises
                    }
                }
        }
    }

    private fun addExercisesToCollection(
        collectionFrom: MutableList<Exercise>,
        collectionTo: MutableList<Exercise>,
        muscleGroup: Muscle,
        difficulty: Difficulty = Difficulty.HARD,
        quantity: Int = 1,
    ) {
        val listOfExercisesForMuscle =
            collectionFrom.filter { exercise ->
                exercise.muscleGroup == muscleGroup && when (difficulty) {
                    Difficulty.EASY -> exercise.difficulty == Difficulty.EASY
                    Difficulty.MEDIUM -> exercise.difficulty == Difficulty.EASY || exercise.difficulty == Difficulty.MEDIUM
                    Difficulty.HARD -> exercise.difficulty == Difficulty.EASY || exercise.difficulty == Difficulty.MEDIUM || exercise.difficulty == Difficulty.HARD
                }
            }
        if (quantity <= listOfExercisesForMuscle.size && quantity > 0) {
            collectionTo.addAll(
                listOfExercisesForMuscle.asSequence().shuffled().take(quantity).toList()
            )
        } else collectionTo.addAll(listOfExercisesForMuscle)
    }

    fun setWorkoutTitle(workoutTitle: String) {
        _workoutTitle.value = workoutTitle
    }

    fun setDifficultySlider(sliderValue: Float) {
        _difficultySlider.value = sliderValue
    }

    fun createWorkout() {
        val workoutUUID = UUID.randomUUID()
        val title = if (_workoutTitle.value.isBlank()) "New Workout" else _workoutTitle.value
        val intermediateExercisesList = _exercisesList.value.toMutableList()
        val finalExercisesList = mutableListOf<Exercise>()
        val workoutDifficulty = if (_difficultySlider.value < 0.33f) {
            Difficulty.EASY
        } else if (_difficultySlider.value > 0.67) {
            Difficulty.HARD
        } else Difficulty.MEDIUM

        //Removing exercises used unchecked equipment
        for (equipment in _equipmentList.value) {
            val index = _equipmentList.value.indexOf(equipment)

            if (!equipmentsState[index].value) {

                //searching for listToRemove
                var exercisesToRemove = emptyList<Exercise>()
                for (equipmentWithExercise in _equipmentsWithExercises.value) {
                    if (equipmentWithExercise.equipment.equipmentId == equipment.equipmentId) {
                        exercisesToRemove = equipmentWithExercise.exercises
                    }
                }

                //removing exercises(listToRemove) from exercisesList
                if (exercisesToRemove.isNotEmpty()) {
                    for (exerciseToRemove in exercisesToRemove) {
                        intermediateExercisesList.remove(exerciseToRemove)
                    }
                }
            }
        }
        //Inserting random exercises to finalExercisesList
        when (workoutDifficulty) {
            Difficulty.EASY -> {
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.BACK,
                    difficulty = Difficulty.EASY,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.CHEST,
                    difficulty = Difficulty.EASY,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.SHOULDERS,
                    difficulty = Difficulty.EASY,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.TRICEPS,
                    difficulty = Difficulty.EASY,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.BICEPS,
                    difficulty = Difficulty.EASY,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.LEGS,
                    difficulty = Difficulty.EASY,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.CORE,
                    difficulty = Difficulty.EASY,
                    quantity = 1
                )
            }
            Difficulty.MEDIUM -> {
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.BACK,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.CHEST,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.SHOULDERS,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.TRICEPS,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.BICEPS,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 2
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.LEGS,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.CORE,
                    difficulty = Difficulty.MEDIUM,
                    quantity = 2
                )
            }
            Difficulty.HARD -> {
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.BACK,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.CHEST,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.SHOULDERS,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.TRICEPS,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.BICEPS,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.LEGS,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
                addExercisesToCollection(
                    collectionFrom = intermediateExercisesList,
                    collectionTo = finalExercisesList,
                    muscleGroup = Muscle.CORE,
                    difficulty = Difficulty.HARD,
                    quantity = 3
                )
            }
        }


        viewModelScope.launch {
            workoutsRepository.insertWorkout(
                workout = Workout(
                    workoutId = workoutUUID,
                    title = title,
                    difficulty = workoutDifficulty
                )
            )
            for (exercise in finalExercisesList) {
                workoutsRepository.insertWorkoutExercise(
                    WorkoutExerciseCrossRef(workoutUUID, exercise.exerciseId)
                )
            }
            for (equipment in _equipmentList.value) {
                val index = _equipmentList.value.indexOf(equipment)

                if (equipmentsState[index].value) {
                    workoutsRepository.insertWorkoutEquipment(
                        WorkoutEquipmentCrossRef(workoutUUID, equipment.equipmentId)
                    )
                }
            }
        }
    }
}