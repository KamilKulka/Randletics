package com.kamilkulka.randletics

enum class RandleticsScreens {
    MainScreen,
    NewWorkoutScreen,
    ExercisesListScreen,
    PreWorkoutScreen;
    companion object{
        fun fromRoute(route: String?): RandleticsScreens =
            when(route){
                MainScreen.name -> MainScreen
                NewWorkoutScreen.name -> NewWorkoutScreen
                ExercisesListScreen.name -> ExercisesListScreen
                PreWorkoutScreen.name -> PreWorkoutScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("$route - no screen found.")
            }
    }
}

