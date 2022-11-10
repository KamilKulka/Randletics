package com.kamilkulka.randletics.screens.preworkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PreWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository):ViewModel() {

    private val _workoutsList = MutableStateFlow<List<Workout>>(emptyList())

    companion object{
        const val TAG="PreWorkoutViewModel"
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllWorkouts().distinctUntilChanged().collect{
                listOfWorkouts ->
                if (listOfWorkouts.isEmpty()){
                    Log.d(TAG,"List of workouts is empty")
                }else{
                    _workoutsList.value = listOfWorkouts
                }
            }
        }
    }

    fun getWorkoutFromUUID(uuid: UUID):Workout{
        return _workoutsList.value.first { workout -> workout.workoutId==uuid }
    }
}