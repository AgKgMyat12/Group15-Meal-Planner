package com.example.hp.mealplanner.data.model

import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.events.DataEvent
import com.example.hp.mealplanner.network.MealPlannerDataAgent
import com.example.hp.mealplanner.network.RetrofitDataAgent
import com.google.gson.JsonArray
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

    fun createOrder (phone: String, address: String, totalPrice: Int, bfID: String, lunchID: String, dinnerID: String, token: String){
        var jsonObject = JsonObject()

        jsonObject.addProperty("Phone", phone)
        jsonObject.addProperty("Address", address)
        jsonObject.addProperty("TotalAmount", totalPrice.toInt())

        var orderDishes1 = JsonObject()
        orderDishes1.addProperty("DishID", bfID)
//        orderDishes1.addProperty("DishQtyInGram", 150)

        var orderDishes2 = JsonObject()
        orderDishes2.addProperty("DishID", lunchID)
//        orderDishes2.addProperty("DishQtyInGram", 150)

        var orderDishes3 = JsonObject()
        orderDishes3.addProperty("DishID", dinnerID)
//        orderDishes3.addProperty("DishQtyInGram", 150)

        var jsonArray = JsonArray()
        jsonArray.add(orderDishes1)
        jsonArray.add(orderDishes2)
        jsonArray.add(orderDishes3)

        var orderDishesArray = JsonObject()
        orderDishesArray.add("OrderDishs", jsonArray)

//        jsonObject.add("OrderDishs",orderDishesArray)

        mDataAgent?.createOrder(jsonObject, token)
    }

    fun getDayID(dayID : String) : MealDishVO{
        return mMealMap?.get(dayID)!!
    }

    fun getMeal() : List<MealDishVO>{
        return ArrayList<MealDishVO>(mMealMap.values)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMealLoaded(mealLoadedEvent: DataEvent.OnSuccessLoadMeal){
        for (meal : MealDishVO in mealLoadedEvent.dishes) {
            mMealMap [meal.id] = meal
        }
    }

}