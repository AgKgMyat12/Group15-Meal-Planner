package com.example.hp.mealplanner.network

import com.example.hp.mealplanner.network.responses.CreateUserResponse
import com.example.hp.mealplanner.network.responses.LoadMealsResponse
import com.example.hp.mealplanner.network.responses.LoginUserResponse
import com.example.hp.mealplanner.network.responses.RegisterUserResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface MealPlannerApi {

    @POST("/Account/Login")
    fun loginUser(@Body loginUser: JsonObject): Call<LoginUserResponse>

    @POST("/Customer/GetMealPlan")
    fun loadMeal(@Header("Authorization") token: String): Call<LoadMealsResponse>

    @POST("/Account/Register")
    fun registerUser(@Body registerUser: JsonObject): Call<RegisterUserResponse>

    @POST("/Customer/Create")
    fun createUserData(@Header("Authorization") token: String, @Body createUserData : JsonObject) : Call<CreateUserResponse>
}