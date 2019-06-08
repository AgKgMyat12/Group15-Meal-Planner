package com.example.hp.mealplanner.network

import com.example.hp.mealplanner.network.responses.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface MealPlannerApi {

    @POST("/account/login")
    fun loginUser(@Body loginUser: JsonObject): Call<LoginUserResponse>

    @POST("/customer/getmealplan")
    fun loadMeal(@Header("Authorization") token: String): Call<LoadMealsResponse>

    @POST("/account/register")
    fun registerUser(@Body registerUser: JsonObject): Call<RegisterUserResponse>

    @POST("/customer/create")
    fun createUserData(@Header("Authorization") token: String, @Body createUserData : JsonObject) : Call<CreateUserResponse>

    @POST("/order/create")
    fun createOrder(@Header("Authorization") token: String, @Body createOrder : JsonObject) : Call<CreateOrderResponse>
}