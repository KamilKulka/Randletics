package com.kamilkulka.randletics.screens.new_workout

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NewWorkoutScreen(
    onAddWorkout: () -> Unit,
    onDiscardButton: () -> Unit,
    viewModel: NewWorkoutViewModel = hiltViewModel()
){

}