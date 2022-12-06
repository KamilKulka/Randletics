package com.kamilkulka.randletics.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Leaderboard
import androidx.compose.material.icons.rounded.SettingsAccessibility
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.RandleticsScreens
import com.kamilkulka.randletics.models.entities.Workout
import com.kamilkulka.randletics.ui.theme.Beige
import com.kamilkulka.randletics.ui.theme.DustyGreen
import com.kamilkulka.randletics.ui.theme.BrightPurple
import com.kamilkulka.randletics.ui.theme.BubblegumPink
import com.kamilkulka.randletics.utils.RowWithIcon

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val workoutList = viewModel.workoutsList.collectAsState().value

    ModalDrawer(drawerContent = {
        DrawerContent(modifier = Modifier.fillMaxSize(),
            enableAddingWorkout = workoutList.size < 5,
            onAddWorkoutButtonClick = {
                navController.navigate(route = RandleticsScreens.NewWorkoutScreen.name)
            },
            onExercisesButtonClick = {
                navController.navigate(route = RandleticsScreens.ExercisesScreen.name)
            },
            onSettingsButtonClick = {
                navController.navigate(route = RandleticsScreens.SettingsScreen.name)
            })
    }) {
        MainScreenContent(workoutList = workoutList,
            countExercisesOfWorkout = { indexOfWorkout ->
                viewModel.countExercisesOf(workoutList[indexOfWorkout])
            },
            onAddWorkout = {
                navController.navigate(route = RandleticsScreens.NewWorkoutScreen.name)
            },
            onWorkoutBoxClick = { indexOfWorkout ->
                navController.navigate(
                    route = RandleticsScreens.PreWorkoutScreen.name +
                            "/${workoutList[indexOfWorkout].workoutId}"
                )
            })
    }
}

@Composable
private fun MainScreenContent(
    workoutList: List<Workout>,
    countExercisesOfWorkout: (Int) -> Int, //TODO simplify function
    onAddWorkout: () -> Unit,
    onWorkoutBoxClick: (Int) -> Unit
) {
    Scaffold { contentPadding ->
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
                        WorkoutBox(
                            workout = workoutList[index],
                            numberOfExercises = countExercisesOfWorkout(index)
                        ) {
                            onWorkoutBoxClick(index)
                        }
                    }
                    if (workoutList.size < 5) {
                        item {
                            AddWorkoutBox {
                                onAddWorkout()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onAddWorkoutButtonClick: () -> Unit,
    onExercisesButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    enableAddingWorkout: Boolean = true
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .scale(1.5f)
                .padding(vertical = 42.dp)
        )
        Divider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 64.dp)
        ) {
            val drawerButtonModifier = Modifier.padding(vertical = 18.dp)
            val drawerButtonTextStyle = TextStyle(fontWeight = FontWeight.Light, fontSize = 20.sp)

            TextButton(
                onClick = {},
                enabled = false,
                modifier = drawerButtonModifier,
                colors = ButtonDefaults.textButtonColors(
                    disabledContentColor = BubblegumPink
                )
            ) {
                Text(
                    text = stringResource(id = R.string.label_main_screen),
                    style = drawerButtonTextStyle
                )
            }
            TextButton(
                onClick = { onAddWorkoutButtonClick() },
                modifier = drawerButtonModifier,
                enabled = enableAddingWorkout,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black.copy(0.4f)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.label_add_workout),
                    style = drawerButtonTextStyle
                )
            }
            TextButton(
                onClick = { onExercisesButtonClick() },
                modifier = drawerButtonModifier,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black.copy(0.4f)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.label_exercises),
                    style = drawerButtonTextStyle
                )
            }
            TextButton(
                onClick = { onSettingsButtonClick() },
                modifier = drawerButtonModifier,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black.copy(0.4f)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.label_settings),
                    style = drawerButtonTextStyle
                )
            }
        }
    }
}

@Composable
fun WorkoutBox(
    workout: Workout,
    numberOfExercises: Int,
    onClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(6.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
            .background(BrightPurple)
            .clickable { onClick() }
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val startColorPoint = Offset(0f, height * 0.35f)
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
            drawPath(path = colorLayerPath, color = Beige)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(text = workout.title, modifier = Modifier, fontSize = 20.sp, color = DustyGreen)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), verticalArrangement = Arrangement.Bottom
        ) {
            RowWithIcon(
                imageVector = Icons.Rounded.Leaderboard,
                text = workout.difficulty.name,
                contentDescription = null
            )
            RowWithIcon(
                imageVector = Icons.Rounded.SettingsAccessibility,
                text = "$numberOfExercises",
                contentDescription = null
            )
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
        border = BorderStroke(width = 8.dp, color = BrightPurple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DustyGreen),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(124.dp)
            )
            Text(text = stringResource(id = R.string.label_add_workout))
        }
    }
}

