package com.kamilkulka.randletics.screens.new_workout

import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {

}