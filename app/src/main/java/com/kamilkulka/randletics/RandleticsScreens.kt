package com.kamilkulka.randletics

enum class RandleticsScreens {
    MainScreen,
    NewWorkoutScreen,
    ExercisesListScreen;
    companion object{
        fun fromRoute(route: String?): RandleticsScreens =
            when(route){
                MainScreen.name -> MainScreen
                NewWorkoutScreen.name -> NewWorkoutScreen
                ExercisesListScreen.name -> ExercisesListScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("$route - no screen found.")
            }
    }
}

