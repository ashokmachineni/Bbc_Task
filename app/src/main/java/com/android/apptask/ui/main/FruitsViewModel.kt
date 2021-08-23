package com.android.apptask.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.apptask.domain.usecases.GetFruitsUseCase
import com.android.apptask.domain.usecases.LogStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitsViewModel @Inject constructor(
    private val getFruitsUseCase: GetFruitsUseCase,
    private val logStatsUseCase: LogStatsUseCase,
) : ViewModel() {

    fun getFruits() = getFruitsUseCase.launch()

    fun logRequestTime(data: Long) = logStatsUseCase.logRequestTime(data)

    fun logError(data: String) = logStatsUseCase.logError(data)

    fun logScreenTime(time: Long) {
        viewModelScope.launch { logStatsUseCase.logScreenTime(time).first() }
    }

    fun calculateDuration(start: Long, end: Long) = end - start

}