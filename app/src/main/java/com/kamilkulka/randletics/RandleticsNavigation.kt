package com.kamilkulka.randletics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kamilkulka.randletics.screens.exercises.ExercisesScreen
import com.kamilkulka.randletics.screens.main.MainScreen
import com.kamilkulka.randletics.screens.main.MainViewModel
import com.kamilkulka.randletics.screens.new_workout.NewWorkoutScreen

@Composable
fun RandleticsNavigation(
    modifier: Modifier = Modifier,
    startDestination: String = RandleticsScreens.MainScreen.name
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RandleticsScreens.MainScreen.name) {
            MainScreen(navController = navController)
        }

        composable(RandleticsScreens.NewWorkoutScreen.name) {
            NewWorkoutScreen(navController = navController)
        }

        composable(RandleticsScreens.ExercisesListScreen.name) {
            ExercisesScreen()
        }
    }
}