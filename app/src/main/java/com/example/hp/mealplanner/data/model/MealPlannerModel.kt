package com.example.hp.mealplanner.data.model

import com.example.hp.mealplanner.network.MealPlannerDataAgent
import com.example.hp.mealplanner.network.RetrofitDataAgent
import com.google.gson.JsonObject

class MealPlannerModel {

    var token: String = ""
    var mDataAgent: MealPlannerDataAgent? = null

    init {
        mDataAgent = RetrofitDataAgent.getInstance()
    }

    companion object {
        private var INSTANCE: MealPlannerModel? = null
        fun getInstance(): MealPlannerModel {
            if (INSTANCE == null) {
                INSTANCE =
                        MealPlannerModel()
            }
            val i = INSTANCE
            return i!!
        }
    }

    fun loginUser(username: String, password: String) {
        var jsonObject = JsonObject()
        jsonObject.addProperty("Username", username)
        jsonObject.addProperty("Password", password)
        mDataAgent?.loginUser(jsonObject)
    }

    fun loadMeals(token: String) {
        mDataAgent?.loadMeal(token)
    }

    fun registerUser(name: String, email : String, password: String){
        var jsonObject = JsonObject()
        jsonObject.addProperty("Name", name)
        jsonObject.addProperty("Username", email)
        jsonObject.addProperty("Password", password)
        mDataAgent?.registerUser(jsonObject)
    }

    fun createUserData(token: String, height: Int, weight : Int, age: Int, gender: Int, goal : Int, active : Int){
        var jsonObject = JsonObject()
        jsonObject.addProperty("Gender", gender)
        jsonObject.addProperty("Height", height)
        jsonObject.addProperty("Age", age)
        jsonObject.addProperty("Weight", weight)
        jsonObject.addProperty("Activity", active)
        jsonObject.addProperty("Goal", goal)
        mDataAgent?.createUserData(jsonObject, token)

    }


}