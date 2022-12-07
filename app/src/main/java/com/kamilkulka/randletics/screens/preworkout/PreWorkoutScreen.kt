package com.kamilkulka.randletics.screens.preworkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.RandleticsScreens
import com.kamilkulka.randletics.utils.AlertPopUp
import com.kamilkulka.randletics.utils.RowWithIcon

@Composable
fun PreWorkoutScreen(
    navController: NavController,
    viewModel: PreWorkoutViewModel = hiltViewModel()
) {
    Scaffold(backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                contentPadding = PaddingValues(12.dp),
                backgroundColor = Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                navController.popBackStack()
                            })
                    if (viewModel.workout.collectAsState().value != null) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    viewModel.setDeletePopUp()
                                })
                    }
                }
            }
        }) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.background, MaterialTheme.colors.primary
                        ), startY = 200f, endY = 600f
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = viewModel.workout.collectAsState().value?.title ?: "",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 80.dp)
            ) {

                RowWithIcon(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp),
                    imageVector = Icons.Rounded.Leaderboard,
                    text = "Difficulty: ${viewModel.getFormattedDifficulty()}"
                )

                RowWithIcon(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp),
                    imageVector = Icons.Rounded.FitnessCenter,
                    text = viewModel.getAllEquipmentsString()
                        ?: stringResource(id = R.string.label_no_equipment)
                )

                RowWithIcon(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp),
                    imageVector = Icons.Rounded.SettingsAccessibility,
                    text = stringResource(id = R.string.label_exercises) + ": ${viewModel.countExercisesOf()}"
                )

                if (viewModel.countExercisesOf() > 0) {
                    if (viewModel.exercisesDropdown.collectAsState().value) {
                        Icon(imageVector = Icons.Rounded.ExpandLess,
                            contentDescription = null,
                            modifier = Modifier.clickable { viewModel.setExercisesDropdown() })
                    } else {
                        Icon(imageVector = Icons.Rounded.ExpandMore,
                            contentDescription = null,
                            modifier = Modifier.clickable { viewModel.setExercisesDropdown() })
                    }
                    if (viewModel.exercisesDropdown.collectAsState().value) {
                        LazyColumn {
                            items(items = viewModel.exercises.value) { exercise ->
                                Text(
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 2.dp,
                                        bottom = 2.dp,
                                        end = 2.dp
                                    ),
                                    text = exercise.name,
                                    color = MaterialTheme.colors.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
        if (viewModel.workout.collectAsState().value != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Button(
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 48.dp, end = 48.dp, top = 32.dp, bottom = 32.dp),
                    onClick = {
                        if (viewModel.workout.value != null) {
                            navController.navigate(
                                route = RandleticsScreens.WorkoutScreen.name +
                                        "/${viewModel.workout.value!!.workoutId}"
                            )
                        }
                    }) {
                    Text(text = stringResource(id = R.string.label_start_workout), fontSize = 24.sp)
                }
            }
        }

        if (viewModel.deletePopUp.collectAsState().value) {
            AlertPopUp(
                contentText = stringResource(id = R.string.delete_workout_inquiry) + "\"${viewModel.workout.collectAsState().value?.title}\"" + "?",
                onConfirmClick = {
                    viewModel.deleteWorkout()
                    navController.navigate(route = RandleticsScreens.MainScreen.name) {
                        popUpTo(RandleticsScreens.PreWorkoutScreen.name) { inclusive = true }
                    }
                },
                onDismissClick = {
                    viewModel.setDeletePopUp()
                },
                onDismissRequest = {
                    viewModel.setDeletePopUp()
                })
        }
    }
}


