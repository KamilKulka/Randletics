package com.kamilkulka.randletics.utils

import androidx.room.TypeConverter
import java.util.*
import java.util.regex.Pattern

class ExercisesListConverter {
    @TypeConverter
    fun fromIDList(listOfID: List<UUID>): String? {
        var uuidStringList: String? = ""
        for (id in listOfID){
            uuidStringList += id.toString()+" "
        }
        return uuidStringList
    }

    @TypeConverter
    fun idListFromString(string: String?): List<UUID>?{
        val WHITESPACE = Pattern.compile("\\s+")

        if (string.isNullOrEmpty()) return emptyList()
        val stringIdList = WHITESPACE.split(string.trim())

        val idList: MutableList<UUID> = mutableListOf<UUID>()
        for (idString in stringIdList){
            if (idString.isNotBlank()){
                idList.add(UUID.fromString(idString))
            }
        }
        return idList
    }
}