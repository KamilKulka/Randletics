package com.kamilkulka.randletics

enum class RandleticsScreens {
    MainScreen,
    NewWorkoutScreen,
    ExercisesScreen,
    PreWorkoutScreen,
    WorkoutScreen,
    SettingsScreen;
    companion object{
        fun fromRoute(route: String?): RandleticsScreens =
            when(route?.substringBefore("/")){
                MainScreen.name -> MainScreen
                NewWorkoutScreen.name -> NewWorkoutScreen
                ExercisesScreen.name -> ExercisesScreen
                PreWorkoutScreen.name  -> PreWorkoutScreen
                WorkoutScreen.name -> WorkoutScreen
                SettingsScreen.name -> SettingsScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("$route - no screen found.")
            }
    }
}

