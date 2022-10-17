package com.kamilkulka.randletics.screens.exercises

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kamilkulka.randletics.screens.main.Greeting

@Composable
fun ExercisesScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting("ExercisesSC")
    }
}