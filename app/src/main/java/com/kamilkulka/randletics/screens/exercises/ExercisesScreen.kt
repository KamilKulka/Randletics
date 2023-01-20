package com.kamilkulka.randletics.screens.exercises

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.models.entities.Exercise
import com.kamilkulka.randletics.ui.theme.Beige
import com.kamilkulka.randletics.utils.FilterDropDown
import com.kamilkulka.randletics.utils.TopIconButton
import com.kamilkulka.randletics.utils.getDifficultyString
import com.kamilkulka.randletics.utils.getMuscleTypeString

@Composable
fun ExercisesScreen(
    navController: NavController,
    viewModel: ExercisesViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopIconButton(imageVector = Icons.Rounded.KeyboardArrowLeft, onClick = {
                    navController.popBackStack()
                })
                TopIconButton(imageVector = Icons.Rounded.DeleteOutline, onClick = {
                    //TODO: Deleting workouts//
                })
            }
            Text(
                text = stringResource(id = R.string.label_exercises) + ":",
                fontWeight = FontWeight.Medium,
                fontSize = 42.sp,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
            )
            Divider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = MaterialTheme.colors.primary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FilterDropDown(
                    modifier = Modifier.weight(1f),
                    filterCategoryName = stringResource(id = R.string.label_difficulty),
                    chosenElement = getDifficultyString(
                        difficulty = viewModel.difficultyFilterSelected.collectAsState().value
                    ),
                    expanded = viewModel.difficultyDropDown.collectAsState().value,
                    onDropButtonClick = { viewModel.setDifficultyDropDown() },
                    onDismissRequest = { viewModel.setDifficultyDropDown() }) {
                    FilterRow(text = stringResource(id = R.string.label_all)) {
                        viewModel.setDifficultyFilter(null)
                        viewModel.setDifficultyDropDown()
                    }
                    viewModel.difficulties.forEach { difficulty ->
                        FilterRow(
                            text = difficulty.name.substring(
                                0,
                                1
                            ) + difficulty.name.substring(1).lowercase()
                        ) {
                            viewModel.setDifficultyFilter(difficulty)
                            viewModel.setDifficultyDropDown()
                        }
                    }
                }
                FilterDropDown(
                    modifier = Modifier.weight(1f),
                    filterCategoryName = stringResource(id = R.string.label_muscle),
                    chosenElement = getMuscleTypeString(
                        muscle = viewModel.muscleFilterSelected.collectAsState().value
                    ),
                    expanded = viewModel.muscleCategoryDropDown.collectAsState().value,
                    onDropButtonClick = { viewModel.setMuscleCategoryDropDown() },
                    onDismissRequest = { viewModel.setMuscleCategoryDropDown() }) {
                    FilterRow(text = stringResource(id = R.string.label_all)) {
                        viewModel.setMuscleFilter(null)
                        viewModel.setMuscleCategoryDropDown()
                    }
                    viewModel.muscleType.forEach { muscleType ->
                        FilterRow(
                            text = muscleType.name.substring(
                                0,
                                1
                            ) + muscleType.name.substring(1).lowercase()
                        ) {
                            viewModel.setMuscleFilter(muscleType)
                            viewModel.setMuscleCategoryDropDown()
                        }
                    }
                }
            }
            Divider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = MaterialTheme.colors.primary
            )

            LazyColumn {
                items(items = viewModel.listOfFilteredExercises.value) { exercise ->
                    ExerciseRow(exercise = exercise)
                }
            }
        }
    }
}

@Composable
fun FilterRow(text: String, onClick: () -> Unit) {
    Text(text = text, modifier = Modifier
        .padding(horizontal = 6.dp, vertical = 2.dp)
        .clickable { onClick() })
}

@Composable
fun ExerciseRow(exercise: Exercise) {
    //TODO: Add more data and link to YT video, string with equipments needed for this workout and so on...//
    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(CornerSize(26.dp)),
        border = BorderStroke(width = 1.5.dp, color = MaterialTheme.colors.onPrimary),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(text = exercise.name, fontWeight = FontWeight.Medium, fontSize = 20.sp, modifier = Modifier.padding(vertical = 4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var formattedMuscleGroup = exercise.muscleGroup.name
                var formattedDifficulty = exercise.difficulty.name

                if (formattedMuscleGroup.isNotEmpty()) {
                    formattedMuscleGroup =
                        formattedMuscleGroup.substring(startIndex = 0, endIndex = 1) + formattedMuscleGroup.substring(1)
                            .lowercase()
                }

                if (formattedDifficulty.isNotEmpty()) {
                    formattedDifficulty =
                        formattedDifficulty.substring(startIndex = 0, endIndex = 1) + formattedDifficulty.substring(1)
                            .lowercase()
                }
                Text(text = formattedMuscleGroup)
                Text(text = formattedDifficulty)
            }
        }
    }
}