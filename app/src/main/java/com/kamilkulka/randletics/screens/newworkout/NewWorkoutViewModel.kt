package com.kamilkulka.randletics.screens.newworkout

import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.EquipmentType
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    private val _workoutTitle = MutableStateFlow("")
    var workoutTitle = _workoutTitle.asStateFlow()

    private val _difficultySlider = MutableStateFlow(0f)
    var difficultySlider = _difficultySlider.asStateFlow()

//    val equipmentList = listOf<Equipment>(
//        Equipment(equipmentType = EquipmentType.DUMBBELLS),
//        Equipment(equipmentType = EquipmentType.BARBELL),
//        Equipment(equipmentType = EquipmentType.FLAT_BENCH),
//        Equipment(equipmentType = EquipmentType.INCLINE_BENCH),
//        Equipment(equipmentType = EquipmentType.DECLINE_BENCH),
//        Equipment(equipmentType = EquipmentType.CURL_BENCH),
//        Equipment(equipmentType = EquipmentType.PULL_UP_BAR),
//        Equipment(equipmentType = EquipmentType.MID_PULL_UP_BAR),
//        Equipment(equipmentType = EquipmentType.RESISTANCE_BANDS),
//        Equipment(equipmentType = EquipmentType.LOW_CABLE_PULLEY),
//        Equipment(equipmentType = EquipmentType.TOP_CABLE_PULLEY)
val equipmentList = listOf<Equipment>(
    Equipment(equipmentType = EquipmentType.BARBELL),
    Equipment(equipmentType = EquipmentType.DECLINE_BENCH),
    Equipment(equipmentType = EquipmentType.CURL_BENCH),
    Equipment(equipmentType = EquipmentType.DUMBBELLS),
    Equipment(equipmentType = EquipmentType.FLAT_BENCH),
    Equipment(equipmentType = EquipmentType.INCLINE_BENCH),
    Equipment(equipmentType = EquipmentType.LOW_CABLE_PULLEY),
    Equipment(equipmentType = EquipmentType.MID_PULL_UP_BAR),
    Equipment(equipmentType = EquipmentType.PULL_UP_BAR),
    Equipment(equipmentType = EquipmentType.TOP_CABLE_PULLEY),
    Equipment(equipmentType = EquipmentType.RESISTANCE_BANDS)
    )



    fun setWorkoutTitle(workoutTitle: String){
        _workoutTitle.value = workoutTitle
    }

    fun setDifficultySlider(sliderValue: Float){
        _difficultySlider.value = sliderValue
    }
}