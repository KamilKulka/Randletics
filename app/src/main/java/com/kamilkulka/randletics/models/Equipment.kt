package com.kamilkulka.randletics.models

data class Equipment(
    val equipmentType: EquipmentType,
    val equipmentName: String = (equipmentType.name.substring(0, 1
    ) + equipmentType.name.substring(1).lowercase()).replace("_"," "),
    val isChecked: Boolean = false
)
