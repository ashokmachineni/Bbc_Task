package com.android.apptask.domain.repositories

import com.android.apptask.domain.models.ApiResponse
import com.android.apptask.domain.models.FruitsResponse
import kotlinx.coroutines.flow.Flow

interface FruitsRepository {

    fun getFruits(): Flow<ApiResponse<FruitsResponse>>

    fun logStats(params: Map<String, Any>): Flow<ApiResponse<Unit>>

}