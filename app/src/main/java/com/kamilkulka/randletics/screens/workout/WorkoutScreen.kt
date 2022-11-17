package com.kamilkulka.randletics.screens.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.ui.theme.DustyGreen
import com.kamilkulka.randletics.ui.theme.SageGreen

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .clickable { navController.popBackStack() },
        color = SageGreen
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .aspectRatio(ratio = 1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    progress = viewModel.progressBar.collectAsState().value,
                    color = DustyGreen,
                    strokeWidth = 12.dp
                )
                
                Text(text = viewModel.timeLeft.collectAsState().value.toString(), fontSize = 48.sp)
            }
            Button(onClick = { viewModel.startCounter() }) {

            }
        }
    }
}