package com.android.apptask.data.repositories

import com.android.apptask.data.map
import com.android.apptask.data.remote.WebService
import com.android.apptask.data.remote.apiRequest
import com.android.apptask.domain.models.ApiResponse
import com.android.apptask.domain.repositories.FruitsRepository
import kotlinx.coroutines.flow.flow

class FruitsRepositoryImpl(private val webService: WebService) : FruitsRepository {

    override fun getFruits() = flow {

        val response = apiRequest { webService.getFruits() }

        val mappedResponse = ApiResponse(
            response.success,
            response.statusCode,
            response.statusMessage,
            response.data?.map(),
            response.requestTime
        )

        emit(mappedResponse)

    }

    override fun logStats(params: Map<String, Any>) = flow {

        val response = apiRequest { webService.sendStats(params) }

        val mappedResponse = ApiResponse(
            response.success,
            response.statusCode,
            response.statusMessage,
            response.data,
            response.requestTime
        )

        emit(mappedResponse)

    }

}