package com.example.hp.mealplanner.network.responses

import com.example.hp.mealplanner.data.vos.MealDishVO
import com.google.gson.annotations.SerializedName

class LoadMealsResponse {

    @SerializedName("code")
    var code : Int = 0

    @SerializedName("message")
    var message : String = ""

    @SerializedName("dishes")
    var dishList : List<MealDishVO> = ArrayList()

//    fun getMealList(): List<MealDishVO> {
//        if (dishList == null) {
//            dishList = java.util.ArrayList<MealDishVO>()
//        }
//        val restaurantList = dishList
//        return restaurantList!!
//    }
}