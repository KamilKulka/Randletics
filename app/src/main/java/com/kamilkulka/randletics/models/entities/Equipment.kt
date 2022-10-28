package com.kamilkulka.randletics.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kamilkulka.randletics.models.EquipmentType
import java.util.*

@Entity(tableName = "equipment_table")
data class Equipment(
    @PrimaryKey
    val equipmentId: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "equipment_type")
    val equipmentType: EquipmentType,
    @ColumnInfo(name = "equipment_name")
    val equipmentName: String = (equipmentType.name.substring(0, 1
    ) + equipmentType.name.substring(1).lowercase()).replace("_"," ")

)
