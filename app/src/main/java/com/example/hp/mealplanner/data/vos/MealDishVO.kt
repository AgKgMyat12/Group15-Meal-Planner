package com.example.hp.mealplanner.data.vos

import com.google.gson.annotations.SerializedName

class MealDishVO {

    @SerializedName ("breakfastData")
    var breakfastData : BreakfastDataVO? = null

    @SerializedName("lunchData")
    var lunchData : LunchDataVO? = null

    @SerializedName("dinnerData")
    var dinnerData : DinnerDataVO? = null

    @SerializedName("day")
    var day : String = ""
}