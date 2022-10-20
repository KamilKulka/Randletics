package com.kamilkulka.randletics.screens.new_workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kamilkulka.randletics.ui.theme.DustyRose
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NewWorkoutScreen(
//    onAddWorkout: () -> Unit,
//    onDiscardButton: () -> Unit,
    navController: NavController,
    viewModel: NewWorkoutViewModel = hiltViewModel()
){
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp),color = DustyRose) {
        Scaffold(topBar = {
            TopAppBar(elevation = 0.dp,contentPadding = PaddingValues(12.dp), backgroundColor = Color.Transparent) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                        navController.popBackStack()
                    })

            }
        }) { contentPadding ->
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)) {

            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun NewWorkoutScreenPreview(){
    Scaffold(topBar = {
            TopAppBar(elevation = 0.dp,contentPadding = PaddingValues(12.dp), backgroundColor = Color.Transparent) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier.size(48.dp).clickable {
//                        navController.popBackStack()
                    })

            }
        }) { contentPadding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {

        }
    }
}