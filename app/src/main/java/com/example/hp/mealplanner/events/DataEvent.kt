package com.example.hp.mealplanner.events

import android.util.Log
import com.example.hp.mealplanner.data.vos.MealDishVO

class DataEvent {
    class OnSuccessLoginEvent(val token: String)
    class OnSuccessLoadMeal(val dishes : List<MealDishVO>)
    class OnSuccessRegisterEvent(val token: String)
    class OnSuccessCreateUserDataEvent(val message : String)
    class EmptyLoadedEvent (val errorMsg:String)
    class OnSuccessOrder(val message : String)
}
