package com.kamilkulka.randletics.screens.newworkout

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.ui.theme.Celadon
import com.kamilkulka.randletics.ui.theme.DustyRose
import com.kamilkulka.randletics.ui.theme.Ivory
import com.kamilkulka.randletics.ui.theme.SageGreen
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

@Composable
fun NewWorkoutScreen(
    navController: NavController,
    viewModel: NewWorkoutViewModel = hiltViewModel()
) {
    val equipmentList = viewModel.equipmentList.collectAsState().value
    Scaffold(backgroundColor = DustyRose,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                contentPadding = PaddingValues(12.dp),
                backgroundColor = Color.Transparent
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Add Workout", fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
                }
            }
        }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            item {
                FillInContentBox(title = "Title:") {
                    BasicTextField(
                        textStyle = TextStyle(fontSize = 20.sp),
                        value = viewModel.workoutTitle.collectAsState().value,
                        onValueChange = viewModel::setWorkoutTitle
                    )
                    Divider()
                }
            }
            item {
                FillInContentBox(title = "Difficulty:") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Easy")
                        Text(text = "Medium")
                        Text(text = "Hard")
                    }
                    Slider(
                        value = viewModel.difficultySlider.collectAsState().value,
                        onValueChange = viewModel::setDifficultySlider,
                        steps = 1,
                        colors = SliderDefaults.colors(
                            thumbColor = SageGreen,
                            activeTickColor = Ivory,
                            inactiveTickColor = Ivory,
                            activeTrackColor = Ivory,
                            inactiveTrackColor = Ivory
                        )
                    )
                }

            }
            item {
                FillInContentBox(title = "Equipment:") {
                    for (equipment in equipmentList) {
                        CheckboxWithText(
                            text = equipment.equipmentName,
                            checked = viewModel.equipmentsState[equipmentList.indexOf(equipment)].collectAsState().value,
                            onCheckedChange = {
                                viewModel.equipmentsState[equipmentList.indexOf(equipment)].value =
                                    !viewModel.equipmentsState[equipmentList.indexOf(equipment)].value
                            })
                    }
                }
            }
            item {
                FillInContentBox() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 0.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .clickable {
                                    viewModel.createWorkout()
                                    navController.popBackStack()
                                },
                            color = SageGreen,
                            shape = CircleShape
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Add Workout", fontSize = 24.sp, color = Ivory)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.clickable {
                                    navController.popBackStack()
                                },
                                text = "Discard Workout",
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.error
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun FillInContentBox(title: String = "", content: @Composable () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
            .background(Celadon),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (title.isNotEmpty()) {
                Text(text = title, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
            content()
        }

    }
}


@Composable
fun CheckboxWithText(
    text: String = "Sample",
    checked: Boolean = false,
    onCheckedChange: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange() },
            colors = CheckboxDefaults.colors(
                checkedColor = SageGreen,
                uncheckedColor = Ivory,
                checkmarkColor = Ivory
            )
        )
        Text(text = text, fontSize = 18.sp)
    }
}

@Composable
fun FillInContentBoxPreview() {
    FillInContentBox(title = "Title:")
}

@Composable
fun NewWorkoutScreenPreview() {
    Scaffold(topBar = {
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
//                        navController.popBackStack()
                    })

        }
    }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

        }
    }
}