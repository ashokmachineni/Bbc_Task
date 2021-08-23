package com.android.apptask.data

import com.android.apptask.data.models.Fruit
import com.android.apptask.data.models.FruitsResponse


fun Fruit.map() = com.android.apptask.domain.models.Fruit(
    type = type, price = price, weight = weight
)

fun FruitsResponse.map() = com.android.apptask.domain.models.FruitsResponse(
    fruit = fruit?.map { it?.map() }
)