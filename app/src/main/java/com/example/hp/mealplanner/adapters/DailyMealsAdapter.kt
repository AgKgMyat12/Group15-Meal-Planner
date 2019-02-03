package com.example.hp.mealplanner.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.viewholders.DailyMealsViewHolders

class DailyMealsAdapter(context : Context) : BaseRecyclerAdapter<DailyMealsViewHolders, MealDishVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyMealsViewHolders {
        val restaurantItemView = mLayoutInflator?.inflate(R.layout.view_item_daily_meals, parent, false)
        return DailyMealsViewHolders(restaurantItemView!!)
    }
}