package com.kamilkulka.randletics.screens.exercises

import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository):ViewModel() {

}