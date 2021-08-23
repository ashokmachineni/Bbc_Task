package com.android.apptask.data.models


import com.google.gson.annotations.SerializedName

data class Fruit(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("weight")
    val weight: Double? = null
)