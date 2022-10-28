package com.kamilkulka.randletics.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kamilkulka.randletics.models.EquipmentType

@Entity(tableName = "equipment_table")
data class Equipment(
    @PrimaryKey(autoGenerate = true)
    val equipmentType: EquipmentType= EquipmentType.NO_EQUIPMENT,
    @ColumnInfo(name = "equipment_name")
    val equipmentName: String = (equipmentType.name.substring(0, 1
    ) + equipmentType.name.substring(1).lowercase()).replace("_"," ")
)
