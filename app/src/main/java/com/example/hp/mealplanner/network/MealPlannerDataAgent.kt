package com.example.hp.mealplanner.network

import com.google.gson.JsonObject

interface MealPlannerDataAgent {

    fun loginUser(jsonObject: JsonObject)
    fun loadMeal(token: String)
    fun registerUser(jsonObject: JsonObject)
    fun createUserData(jsonObject: JsonObject, token: String)
}