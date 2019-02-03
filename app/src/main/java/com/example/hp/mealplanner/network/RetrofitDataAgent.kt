package com.example.hp.mealplanner.network

import com.example.hp.mealplanner.events.DataEvent
import com.example.hp.mealplanner.events.ErrorEvent
import com.example.hp.mealplanner.network.responses.CreateUserResponse
import com.example.hp.mealplanner.network.responses.LoadMealsResponse
import com.example.hp.mealplanner.network.responses.LoginUserResponse
import com.example.hp.mealplanner.network.responses.RegisterUserResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgent : MealPlannerDataAgent{

    private val mMealPlannerApi : MealPlannerApi

    companion object {
        private var INSTANCE : RetrofitDataAgent ?= null
        fun getInstance() : RetrofitDataAgent{
            if (INSTANCE == null){
                INSTANCE = RetrofitDataAgent()
            }
            val i = INSTANCE
            return i!!
        }
    }

    private constructor(){
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://naingyeaung1998-001-site1.dtempurl.com")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()

        mMealPlannerApi = retrofit.create(MealPlannerApi::class.java)
    }

    override fun loginUser(jsonObject: JsonObject) {
        val loginUser : Call<LoginUserResponse> = mMealPlannerApi.loginUser(jsonObject)
        loginUser.enqueue(object : Callback<LoginUserResponse> {
            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<LoginUserResponse>, response: Response<LoginUserResponse>) {
                val loginUserResponse : LoginUserResponse? = response.body()
                if (loginUserResponse != null ){
                    EventBus.getDefault().post(DataEvent.OnSuccessLoginEvent(loginUserResponse.token))
                }
                else{
                    EventBus.getDefault().post(DataEvent.EmptyLoadedEvent("No Token"))
                }
            }

        })
    }

    override fun loadMeal(token : String){
        val loadMeal : Call<LoadMealsResponse> = mMealPlannerApi.loadMeal("Bearer $token")
        loadMeal.enqueue(object : Callback<LoadMealsResponse> {
            override fun onFailure(call: Call<LoadMealsResponse>, t: Throwable) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<LoadMealsResponse>, response: Response<LoadMealsResponse>) {
                val loadMealsResponse : LoadMealsResponse? = response.body()
                if (loadMealsResponse != null ){
                    EventBus.getDefault().post(DataEvent.OnSuccessLoadMeal(loadMealsResponse.dishList))
                }
                else{
                    EventBus.getDefault().post(DataEvent.EmptyLoadedEvent("No Token"))
                }
            }

        })
    }

    override fun registerUser(jsonObject: JsonObject) {
        val registerUser : Call<RegisterUserResponse> = mMealPlannerApi.registerUser(jsonObject)
        registerUser.enqueue(object : Callback<RegisterUserResponse> {
            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<RegisterUserResponse>, response: Response<RegisterUserResponse>) {
                val registerUserResponse : RegisterUserResponse? = response.body()
                if (registerUserResponse != null ){
                    EventBus.getDefault().post(DataEvent.OnSuccessRegisterEvent(registerUserResponse.token))
                }
                else{
                    EventBus.getDefault().post(DataEvent.EmptyLoadedEvent("No Token"))
                }
            }

        })
    }

    override fun createUserData(jsonObject: JsonObject, token: String) {
        val createUserData : Call<CreateUserResponse> = mMealPlannerApi.createUserData("Bearer $token", jsonObject)
        createUserData.enqueue(object : Callback<CreateUserResponse> {
            override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<CreateUserResponse>, response: Response<CreateUserResponse>) {
                val createUserResponse : CreateUserResponse? = response.body()
                if (createUserResponse!!.message == "success" ){
                    EventBus.getDefault().post(DataEvent.OnSuccessCreateUserDataEvent(createUserResponse.message))
                }
                else{
                    EventBus.getDefault().post(DataEvent.EmptyLoadedEvent("No Token"))
                }
            }

        })
    }

}