package com.kamilkulka.randletics

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kamilkulka.randletics.screens.exercises.ExercisesScreen
import com.kamilkulka.randletics.screens.main.MainScreen
import com.kamilkulka.randletics.screens.newworkout.NewWorkoutScreen
import com.kamilkulka.randletics.screens.preworkout.PreWorkoutScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.kamilkulka.randletics.screens.settings.SettingsScreen
import com.kamilkulka.randletics.screens.workout.WorkoutScreen

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
        val animationDuration = 350
        composable(route = RandleticsScreens.MainScreen.name,
            exitTransition = {
                when (targetState.destination.route) {
                    "${RandleticsScreens.PreWorkoutScreen.name}/{workoutId}" -> slideOutOfContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.Start
                    )
                    RandleticsScreens.NewWorkoutScreen.name -> fadeOut(
                        animationSpec = snap(
                            delayMillis = animationDuration
                        )
                    )
                    else -> null
                }
            }, popEnterTransition = {
                when (initialState.destination.route) {
                    "${RandleticsScreens.PreWorkoutScreen.name}/{workoutId}" -> slideIntoContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.End
                    )
                    RandleticsScreens.NewWorkoutScreen.name -> fadeIn(
                        animationSpec = tween(
                            durationMillis = animationDuration
                        )
                    )
                    else -> null
                }
            }) {
            MainScreen(navController = navController)
        }

        composable(
            route = RandleticsScreens.NewWorkoutScreen.name,
            enterTransition = {
                when (initialState.destination.route) {
                    RandleticsScreens.MainScreen.name -> scaleIn(
                        animationSpec = tween(
                            durationMillis = animationDuration
                        ),
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0.75f
                        )
                    )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    RandleticsScreens.MainScreen.name -> scaleOut(
                        animationSpec = tween(durationMillis = animationDuration),
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0.75f
                        )
                    )
                    else -> null
                }
            }
        ) {
            NewWorkoutScreen(navController = navController)
        }

        composable(
            route = RandleticsScreens.PreWorkoutScreen.name + "/{workoutId}",
            arguments = listOf(navArgument(name = "workoutId") {
                type = NavType.StringType
            }), exitTransition = {
                when (targetState.destination.route) {
                    RandleticsScreens.MainScreen.name -> slideOutOfContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.End
                    )
                    "${RandleticsScreens.WorkoutScreen.name}/{workoutId}" -> slideOutOfContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.Up
                    )
                    else -> null
                }
            }, enterTransition = {
                when (initialState.destination.route) {
                    RandleticsScreens.MainScreen.name -> slideIntoContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.Start
                    )
                    else -> null
                }
            }, popEnterTransition = {
                when (initialState.destination.route) {
                    RandleticsScreens.MainScreen.name -> slideIntoContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.Start
                    )
                    else -> null
                }
            }
        ) {
            PreWorkoutScreen(
                navController = navController
            )
        }

        composable(
            route = RandleticsScreens.WorkoutScreen.name + "/{workoutId}",
            arguments = listOf(
                navArgument(name = "workoutId") {
                    type = NavType.StringType
                }),
            enterTransition = {
                when (initialState.destination.route) {
                    "${RandleticsScreens.PreWorkoutScreen.name}/{workoutId}" -> slideIntoContainer(
                        animationSpec = tween(durationMillis = animationDuration),
                        towards = AnimatedContentScope.SlideDirection.Up
                    )
                    else -> null
                }
            }) {
            BackHandler(true) {}
            WorkoutScreen(
                navController = navController
            )
        }

        composable(RandleticsScreens.ExercisesScreen.name) {
            ExercisesScreen(navController = navController)
        }
        
        composable(RandleticsScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }
    }
}