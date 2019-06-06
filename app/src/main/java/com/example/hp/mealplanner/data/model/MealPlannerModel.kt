package com.example.hp.mealplanner.data.model

import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.events.DataEvent
import com.example.hp.mealplanner.network.MealPlannerDataAgent
import com.example.hp.mealplanner.network.RetrofitDataAgent
import com.google.gson.JsonObject
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.HashMap

class MealPlannerModel {

    private var mMealMap: HashMap<String, MealDishVO> = HashMap()

    var token: String = ""
    var mDataAgent: MealPlannerDataAgent? = null

    init {
        mDataAgent = RetrofitDataAgent.getInstance()
        EventBus.getDefault().register(this)
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

    fun getDayID(dayID : String) : MealDishVO{
        return mMealMap?.get(dayID)!!
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMealLoaded(mealLoadedEvent: DataEvent.OnSuccessLoadMeal){
        for (meal : MealDishVO in mealLoadedEvent.dishes) {
            mMealMap [meal.id] = meal
        }
    }

}