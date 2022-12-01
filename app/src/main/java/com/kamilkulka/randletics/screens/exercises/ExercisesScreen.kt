package com.kamilkulka.randletics.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.ui.theme.Beige
import com.kamilkulka.randletics.utils.FilterDropDown
import com.kamilkulka.randletics.utils.TopIconButton

@Composable
fun ExercisesScreen(navController: NavController,
                    viewModel: ExercisesViewModel = hiltViewModel()){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                TopIconButton(imageVector = Icons.Rounded.KeyboardArrowLeft, onClick = {
                    navController.popBackStack()
                })
                TopIconButton(imageVector = Icons.Rounded.DeleteOutline, onClick = {

                })
            }
            Text(text = "Exercises"+":",
                fontWeight = FontWeight.Medium,
                fontSize = 42.sp,
                modifier = Modifier.padding(start = 32.dp,end = 32.dp, bottom = 32.dp))
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround) {
                FilterDropDown(
                    filterCategoryName = "Difficulty",
                    chosenElement = "All",
                    expanded = viewModel.difficultyDropDown.collectAsState().value,
                    onDropButtonClick = { viewModel.setDifficultyDropDown() },
                    onDismissRequest = { viewModel.setDifficultyDropDown() }) {
                    Text(text = "aaa")
                    Text(text = "bbb")
                    Text(text = "ccc")
                }
                FilterDropDown(
                    filterCategoryName = "Muscle",
                    chosenElement = "All",
                    expanded = viewModel.muscleCategoryDropDown.collectAsState().value,
                    onDropButtonClick = { viewModel.setMuscleCategoryDropDown() },
                    onDismissRequest = { viewModel.setMuscleCategoryDropDown() }) {
                    Text(text = "aaa")
                    Text(text = "bbb")
                    Text(text = "ccc")
                }
            }
            Divider()
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise){
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Beige, shape = RoundedCornerShape(20.dp))) {
        Text(text = "Pull ups")
        
    }
}