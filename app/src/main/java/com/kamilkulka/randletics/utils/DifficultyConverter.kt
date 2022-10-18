package com.kamilkulka.randletics.utils

import androidx.room.TypeConverter
import com.kamilkulka.randletics.models.Difficulty

class DifficultyConverter {

    @TypeConverter
    fun toDifficulty(value: String) = enumValueOf<Difficulty>(value)

    @TypeConverter
    fun fromDifficulty(value: Difficulty) = value.name
}