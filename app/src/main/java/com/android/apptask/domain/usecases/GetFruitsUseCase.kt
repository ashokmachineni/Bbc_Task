package com.android.apptask.domain.usecases

import com.android.apptask.domain.repositories.FruitsRepository

class GetFruitsUseCase(private val fruitsRepository: FruitsRepository) {

    fun launch() = fruitsRepository.getFruits()

}