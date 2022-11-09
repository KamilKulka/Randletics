package com.kamilkulka.randletics

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kamilkulka.randletics.screens.exercises.ExercisesScreen
import com.kamilkulka.randletics.screens.main.MainScreen
import com.kamilkulka.randletics.screens.newworkout.NewWorkoutScreen
import com.kamilkulka.randletics.screens.preworkout.PreWorkoutScreen
import java.util.*
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RandleticsNavigation(
    modifier: Modifier = Modifier,
    startDestination: String = RandleticsScreens.MainScreen.name
) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = RandleticsScreens.MainScreen.name,
            exitTransition = {
                when (targetState.destination.route) {
                    "${RandleticsScreens.PreWorkoutScreen.name}/{workoutId}" -> slideOutOfContainer(
                        animationSpec = tween(durationMillis = 300),
                        towards = AnimatedContentScope.SlideDirection.Start
                    )
                    else -> null
                }
            }, enterTransition = {
                when (initialState.destination.route) {
                    "${RandleticsScreens.PreWorkoutScreen.name}/{workoutId}" -> slideIntoContainer(
                        animationSpec = tween(durationMillis = 300),
                        towards = AnimatedContentScope.SlideDirection.End
                    )
                    else -> null
                }
            }, popEnterTransition = {
                when (initialState.destination.route) {
                    "${RandleticsScreens.PreWorkoutScreen.name}/{workoutId}" -> slideIntoContainer(
                        animationSpec = tween(durationMillis = 300),
                        towards = AnimatedContentScope.SlideDirection.End
                    )
                    else -> null
                }
            }) {
            MainScreen(navController = navController)
        }

        composable(RandleticsScreens.NewWorkoutScreen.name) {
            NewWorkoutScreen(navController = navController)
        }

        composable(
            route = RandleticsScreens.PreWorkoutScreen.name + "/{workoutId}",
            arguments = listOf(navArgument(name = "workoutId") {
                type = NavType.StringType
            }), exitTransition = {
                when (targetState.destination.route) {
                    RandleticsScreens.MainScreen.name -> slideOutOfContainer(
                        animationSpec = tween(durationMillis = 300),
                        towards = AnimatedContentScope.SlideDirection.End
                    )
                    else -> null
                }
            }, enterTransition = {
                when (initialState.destination.route) {
                    RandleticsScreens.MainScreen.name -> slideIntoContainer(
                        animationSpec = tween(durationMillis = 300),
                        towards = AnimatedContentScope.SlideDirection.Start
                    )
                    else -> null
                }
            }, popEnterTransition = {
                when (initialState.destination.route) {
                    RandleticsScreens.MainScreen.name -> slideIntoContainer(
                        animationSpec = tween(durationMillis = 300),
                        towards = AnimatedContentScope.SlideDirection.Start
                    )
                    else -> null
                }
            }
        ) {
            val workoutId = UUID.fromString(it.arguments?.getString("workoutId"))
            PreWorkoutScreen(workoutId = workoutId, navController = navController)
        }

        composable(RandleticsScreens.ExercisesListScreen.name) {
            ExercisesScreen()
        }
    }
}