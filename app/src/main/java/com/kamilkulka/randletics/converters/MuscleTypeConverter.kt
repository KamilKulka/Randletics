package com.kamilkulka.randletics.converters

import androidx.room.TypeConverter
import com.kamilkulka.randletics.models.Muscle

class MuscleTypeConverter {
    @TypeConverter
    fun fromMuscleType(muscle: Muscle): String = muscle.toString()

    @TypeConverter
    fun muscleTypeFromString(string: String): Muscle = Muscle.valueOf(string)
}