package com.example.hp.mealplanner.adapters

import android.content.Context
import android.view.ViewGroup
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.controller.DayItemController
import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.viewholders.DailyViewHolder

class DailyAdapter(context : Context, dayItemController: DayItemController) : BaseAdapter<DailyViewHolder, MealDishVO>(context) {

    private var mDayItemController : DayItemController? = null

    init {
        mDayItemController = dayItemController
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val restaurantItemView = mLayoutInflator?.inflate(R.layout.view_item_daily, parent, false)
        return DailyViewHolder(restaurantItemView!!, mDayItemController!!)
    }

}