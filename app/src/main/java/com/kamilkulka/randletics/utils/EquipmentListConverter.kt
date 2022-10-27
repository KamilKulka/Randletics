package com.kamilkulka.randletics.utils

import androidx.room.TypeConverter
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.EquipmentType
import java.util.regex.Pattern

class EquipmentListConverter {
    @TypeConverter
    fun fromEquipmentList(listOfEquipment: MutableList<Equipment>): String? {
        var equipmentStringList: String? = ""
        for (equipment in listOfEquipment){
            equipmentStringList += equipment.equipmentType.toString()+" "
        }
        return equipmentStringList
    }

    @TypeConverter
    fun equipmentListFromString(string: String?): MutableList<Equipment>{
        val WHITESPACE = Pattern.compile("\\s+")

        if (string.isNullOrEmpty()) return mutableListOf<Equipment>()
        val stringEquipmentList = WHITESPACE.split(string.trim())

        val equipmentList: MutableList<Equipment> = mutableListOf<Equipment>()
        for (equipmentString in stringEquipmentList){
            if (equipmentString.isNotBlank()){
                equipmentList.add(
                    Equipment(
                    equipmentType = EquipmentType.valueOf(equipmentString))
                )
            }
        }
        return equipmentList
    }
}