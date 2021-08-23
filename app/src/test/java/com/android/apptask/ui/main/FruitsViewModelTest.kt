package com.android.apptask.ui.main

import com.android.apptask.domain.usecases.GetFruitsUseCase
import com.android.apptask.domain.usecases.LogStatsUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FruitsViewModelTest {

    private val fakeFruitsRepository = FakeFruitsRepository()
    private lateinit var getFruitsUseCase: GetFruitsUseCase
    private lateinit var logStatsUseCase: LogStatsUseCase
    private lateinit var viewModel: FruitsViewModel

    @Before
    fun setUp() {
        getFruitsUseCase = GetFruitsUseCase(fakeFruitsRepository)
        logStatsUseCase = LogStatsUseCase(fakeFruitsRepository)
        viewModel = FruitsViewModel(getFruitsUseCase, logStatsUseCase)
    }


    @Test
    fun getFruitsCheckResponse(): Unit = runBlocking {

        val firstItem = viewModel.getFruits().first()
        assertThat(firstItem).isEqualTo(FakeFruitsRepository.fakeResponse)

    }


    @Test
    fun checkCalculateDuration_return_1000() {

        val start = 2000L
        val end = 3000L

        val result = viewModel.calculateDuration(
            start = start, end = end
        )

        assertThat(result).isEqualTo(end - start)
    }


    @Test
    fun checkCalculateDuration_return_7000() {

        val start = 1000L
        val end = 8000L

        val result = viewModel.calculateDuration(
            start = start, end = end
        )

        assertThat(result).isEqualTo(end - start)
    }

    @Test
    fun logRequestTime_checkFlowEmit() = runBlocking {

        val response = viewModel.logRequestTime(1000).first()

        assertThat(response).isEqualTo(FakeFruitsRepository.fakeLogResponse)

    }

    @Test
    fun logError_checkFlowEmit() = runBlocking {

        val response = viewModel.logError("any data").first()

        assertThat(response).isEqualTo(FakeFruitsRepository.fakeLogResponse)

    }


}