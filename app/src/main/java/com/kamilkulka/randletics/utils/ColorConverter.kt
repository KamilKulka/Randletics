package com.kamilkulka.randletics.utils

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorConverter {

    @TypeConverter
    fun fromColor(color: Color): Long = color.value.toLong()

    @TypeConverter
    fun  toColor(value: Long): Color = Color(value)
}