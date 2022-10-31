package com.kamilkulka.randletics.screens.newworkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamilkulka.randletics.models.entities.Equipment
import com.kamilkulka.randletics.models.EquipmentType
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewWorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository) :
    ViewModel() {
    private val _workoutTitle = MutableStateFlow("")
    var workoutTitle = _workoutTitle.asStateFlow()

    private val _difficultySlider = MutableStateFlow(0f)
    var difficultySlider = _difficultySlider.asStateFlow()

    private val _equipmentList = MutableStateFlow<List<Equipment>>(emptyList())
    val equipmentList = _equipmentList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            workoutsRepository.getAllEquipments().distinctUntilChanged().collect(){
                listOfEquipments ->
                if(listOfEquipments.isEmpty()){
                    Log.d("Empty","Equipment list is empty!")
                }else{
                    _equipmentList.value=listOfEquipments
                }
            }
        }
    }
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
//val equipmentList = workoutsRepository.getAllEquipments()



    fun setWorkoutTitle(workoutTitle: String){
        _workoutTitle.value = workoutTitle
    }

    fun setDifficultySlider(sliderValue: Float){
        _difficultySlider.value = sliderValue
    }
}