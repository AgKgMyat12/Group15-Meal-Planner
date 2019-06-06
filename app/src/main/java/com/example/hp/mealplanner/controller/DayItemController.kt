package com.example.hp.mealplanner.controller

import com.example.hp.mealplanner.data.vos.MealDishVO

interface DayItemController {
    fun onTapDay(mealDishVO: MealDishVO)
}