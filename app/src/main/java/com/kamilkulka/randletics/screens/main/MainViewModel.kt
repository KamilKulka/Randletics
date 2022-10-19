package com.kamilkulka.randletics.screens.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.Workout
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository): ViewModel() {
    private val _workoutsList = MutableStateFlow<List<Workout>>(emptyList())
    val workoutsList = _workoutsList.asStateFlow()

    init{
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAll().distinctUntilChanged().collect(){
                listOfWorkouts ->
                if (listOfWorkouts.isEmpty()){
                    Log.d("Empty","Empty list")
                }else{
                    _workoutsList.value=listOfWorkouts
                }
            }
        }
    }
}
