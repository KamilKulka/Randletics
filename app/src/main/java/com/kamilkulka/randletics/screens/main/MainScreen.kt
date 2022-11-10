package com.kamilkulka.randletics.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.RandleticsScreens
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.ui.theme.Celadon
import com.kamilkulka.randletics.ui.theme.DustyGreen
import com.kamilkulka.randletics.ui.theme.SageGreen

@Composable
fun MainScreen(
//    onAddWorkout: () -> Unit,
//    onWorkoutClick: (Workout) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val workoutList = viewModel.workoutsList.collectAsState().value
    Scaffold() { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            color = DustyGreen
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    painter = rememberImagePainter(
                        R.drawable.main_photo
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 5.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 100.dp
                    ),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(workoutList.size) { index ->
                        WorkoutBox(workout = workoutList[index]){
                            navController.navigate(route = RandleticsScreens.PreWorkoutScreen.name + "/${workoutList[index].workoutId}")
                        }
                    }
                    if (workoutList.size < 4) {
                        item {
                            AddWorkoutBox() {
                                navController.navigate(route = RandleticsScreens.NewWorkoutScreen.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutBox(
    workout: Workout,
    onClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(6.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
            .background(SageGreen)
            .clickable { onClick() }
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val startColorPoint = Offset(0f, height * 0.2f)
        val endColorPoint = Offset(width.toFloat(), height * 0.6f)
        //ColoredPath
        val colorLayerPath = Path().apply {
            moveTo(startColorPoint.x, startColorPoint.y)
            lineTo(endColorPoint.x, endColorPoint.y)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = colorLayerPath, color = Celadon)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(text = workout.title, modifier = Modifier.align(Alignment.TopStart), color = DustyGreen)
        }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), verticalArrangement = Arrangement.Bottom) {
                Row(modifier = Modifier.wrapContentSize()) {
                    Icon(imageVector = Icons.Filled.Leaderboard , contentDescription = "Difficulty")
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = workout.difficulty.name)
                }
                Row(modifier = Modifier.wrapContentSize()) {
                    Icon(imageVector = Icons.Filled.FitnessCenter , contentDescription = "Difficulty")
                    Spacer(modifier = Modifier.size(8.dp))
                    //TODO number of exercises
                    Text(text = "12")
                }
            }

    }
}

@Composable
fun AddWorkoutBox(onAddWorkout: () -> Unit) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(6.dp)
            .clickable {
                onAddWorkout()
            },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        border = BorderStroke(width = 8.dp, color = SageGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DustyGreen),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Sharp.Add,
                contentDescription = "Add Workout",
                modifier = Modifier.size(124.dp)
            )
            Text(text = "Add Workout")
        }
    }
}

