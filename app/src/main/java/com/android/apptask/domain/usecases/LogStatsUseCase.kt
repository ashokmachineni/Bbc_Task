package com.android.apptask.domain.usecases

import com.android.apptask.domain.models.ApiResponse
import com.android.apptask.domain.repositories.FruitsRepository
import kotlinx.coroutines.flow.Flow

class LogStatsUseCase(private val fruitsRepository: FruitsRepository) {

    fun logRequestTime(timeMillis: Long): Flow<ApiResponse<Unit>> {

        val params = hashMapOf<String, Any>().apply {
            this["event"] = "load"
            this["data"] = timeMillis
        }

        return fruitsRepository.logStats(params)

    }

    fun logScreenTime(timeMillis: Long): Flow<ApiResponse<Unit>> {

        val params = hashMapOf<String, Any>().apply {
            this["event"] = "display"
            this["data"] = timeMillis
        }

        return fruitsRepository.logStats(params)

    }

    fun logError(data: String): Flow<ApiResponse<Unit>> {

        val params = hashMapOf<String, Any>().apply {
            this["event"] = "error"
            this["data"] = data
        }

        return fruitsRepository.logStats(params)

    }

}