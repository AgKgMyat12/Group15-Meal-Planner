package com.example.hp.mealplanner.data.vos

import com.google.gson.annotations.SerializedName

class MealDishVO {

    @SerializedName("id")
    var id : String =  ""

    @SerializedName ("breakfastData")
    var breakfastData : BreakfastDataVO? = null

    @SerializedName("lunchData")
    var lunchData : LunchDataVO? = null

    @SerializedName("dinnerData")
    var dinnerData : DinnerDataVO? = null

    @SerializedName("day")
    var day : String = ""

    @SerializedName("numOfMeals")
    var numOfMeals : String = ""

    @SerializedName("totalCalories")
    var totalCalories : Int = 0

    @SerializedName("price")
    var price : String = ""

    @SerializedName("picture")
    var picture : String = ""
}