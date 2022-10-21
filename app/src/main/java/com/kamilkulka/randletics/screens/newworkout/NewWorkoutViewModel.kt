package com.kamilkulka.randletics.screens.newworkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.models.Equipment
import com.kamilkulka.randletics.models.EquipmentType
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    val equipmentList = listOf<Equipment>(
        Equipment(equipmentType = EquipmentType.DUMBBELLS),
        Equipment(equipmentType = EquipmentType.BARBELL),
        Equipment(equipmentType = EquipmentType.FLAT_BENCH),
        Equipment(equipmentType = EquipmentType.INCLINE_BENCH),
        Equipment(equipmentType = EquipmentType.DECLINE_BENCH),
        Equipment(equipmentType = EquipmentType.CURL_BENCH),
        Equipment(equipmentType = EquipmentType.PULL_UP_BAR),
        Equipment(equipmentType = EquipmentType.MID_PULL_UP_BAR),
        Equipment(equipmentType = EquipmentType.RESISTANCE_BANDS),
        Equipment(equipmentType = EquipmentType.LOW_CABLE_PULLEY),
        Equipment(equipmentType = EquipmentType.TOP_CABLE_PULLEY),
    )
    private val _workoutTitle = MutableStateFlow("")
    var workoutTitle = _workoutTitle.asStateFlow()

    fun setWorkoutTitle(workoutTitle: String){
        _workoutTitle.value = workoutTitle
    }

    private val _difficultySlider = MutableStateFlow(0f)
    var difficultySlider = _difficultySlider.asStateFlow()

    fun setDifficultySlider(sliderValue: Float){
        _difficultySlider.value = sliderValue
    }
}