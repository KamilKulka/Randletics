package com.kamilkulka.randletics.screens.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.ui.theme.Celadon

@Composable
fun WorkoutScreen(navController:NavController,
    viewModel: WorkoutViewModel = hiltViewModel()
)   {
        Surface(modifier = Modifier.fillMaxSize().clickable { navController.popBackStack() }, color = Celadon) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "WorkoutScreen!")
            }
        }
    }