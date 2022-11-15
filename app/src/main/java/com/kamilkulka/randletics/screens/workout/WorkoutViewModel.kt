package com.kamilkulka.randletics.screens.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository,
                                           private val savedStateHandle: SavedStateHandle):
    ViewModel() {

}