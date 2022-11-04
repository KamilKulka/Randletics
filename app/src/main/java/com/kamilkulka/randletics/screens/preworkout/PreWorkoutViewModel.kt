package com.kamilkulka.randletics.screens.preworkout

import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository):ViewModel() {

}