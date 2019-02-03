package com.example.hp.mealplanner.data.vos

import com.google.gson.annotations.SerializedName

class BreakfastDataVO {

    @SerializedName("id")
    var id : String = ""

    @SerializedName("name")
    var name : String = ""

    @SerializedName("photo")
    var photo : String = ""

    @SerializedName("breakfastGram")
    var breakfastGram : Int = 0
}