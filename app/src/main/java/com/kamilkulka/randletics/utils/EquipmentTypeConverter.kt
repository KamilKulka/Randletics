package com.kamilkulka.randletics.utils

import androidx.room.TypeConverter
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.EquipmentType
import java.util.regex.Pattern

class EquipmentTypeConverter {
    @TypeConverter
    fun fromEquipmentType(equipmentType: EquipmentType): String = equipmentType.name

    @TypeConverter
    fun equipmentTypeFromString(string: String): EquipmentType =  EquipmentType.valueOf(string)
}