package com.android.apptask.data.models

import com.android.apptask.data.models.Fruit
import com.google.gson.annotations.SerializedName

data class FruitsResponse(

    @SerializedName("fruit")
    var fruit: List<Fruit>? = null

)