package com.example.hp.mealplanner.data.vos

import com.google.gson.annotations.SerializedName

class LunchDataVO {

    @SerializedName("id")
    var id : String = ""

    @SerializedName("name")
    var name : String = ""

    @SerializedName("photo")
    var photo : String = ""

    @SerializedName("lunchGram")
    var lunchGram : Int = 0
}