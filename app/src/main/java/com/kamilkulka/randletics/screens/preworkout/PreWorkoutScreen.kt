package com.kamilkulka.randletics.screens.preworkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.ui.theme.DustyRose

@Composable
fun PreWorkoutScreen(
    navController: NavController,
    viewModel: PreWorkoutViewModel = hiltViewModel()
) {
    Scaffold(backgroundColor = DustyRose,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                contentPadding = PaddingValues(12.dp),
                backgroundColor = Color.Transparent
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            navController.popBackStack()
                        })
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Main Screen")
            }
        }) { contentPadding ->
        Text(
            modifier = Modifier.padding(contentPadding), text = "Pre Workout Screen"
        )
    }
}