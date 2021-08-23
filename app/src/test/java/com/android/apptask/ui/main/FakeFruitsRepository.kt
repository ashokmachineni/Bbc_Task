package com.android.apptask.ui.main

import com.android.apptask.domain.models.ApiResponse
import com.android.apptask.domain.models.Fruit
import com.android.apptask.domain.models.FruitsResponse
import com.android.apptask.domain.repositories.FruitsRepository
import kotlinx.coroutines.flow.flow

class FakeFruitsRepository : FruitsRepository {

    companion object {


        val fakeLogResponse = ApiResponse(
            success = true,
            statusCode = 200,
            statusMessage = "success",
            data = Unit
        )

        val fruits = listOf(
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
            Fruit(),
        )

        val fakeResponse = ApiResponse(
            success = true,
            statusCode = 200,
            statusMessage = "success",
            data = FruitsResponse(fruits)
        )

    }



    override fun getFruits() = flow {

        emit(fakeResponse)

    }

    override fun logStats(params: Map<String, Any>) = flow {
        emit(fakeLogResponse)
    }
}