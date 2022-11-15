package com.kamilkulka.randletics

enum class RandleticsScreens {
    MainScreen,
    NewWorkoutScreen,
    ExercisesListScreen,
    PreWorkoutScreen,
    WorkoutScreen;
    companion object{
        fun fromRoute(route: String?): RandleticsScreens =
            when(route?.substringBefore("/")){
                MainScreen.name -> MainScreen
                NewWorkoutScreen.name -> NewWorkoutScreen
                ExercisesListScreen.name -> ExercisesListScreen
                PreWorkoutScreen.name  -> PreWorkoutScreen
                WorkoutScreen.name -> WorkoutScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("$route - no screen found.")
            }
    }
}

