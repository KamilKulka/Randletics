package com.kamilkulka.randletics.screens.workout

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kamilkulka.randletics.repository.WorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(private val workoutsRepository: WorkoutsRepository,
                                           private val savedStateHandle: SavedStateHandle):
    ViewModel() {

    companion object{
        const val SEC_SPAN = 45
    }

    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft = _timeLeft.asStateFlow()

    private val _progressBar = MutableStateFlow(1f)
    val progressBar = _progressBar.asStateFlow()

        private val counter = object: CountDownTimer(SEC_SPAN *1000L,1){
        override fun onTick(millisUntilFinished: Long) {
            _timeLeft.value = millisUntilFinished/1000
            _progressBar.value = millisUntilFinished.toFloat()/ (SEC_SPAN*1000).toFloat()
        }

        override fun onFinish() {

        }
    }


    fun startCounter(){
        counter.start()
    }
}