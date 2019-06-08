package com.example.hp.mealplanner.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.hp.mealplanner.controller.DayItemController
import com.example.hp.mealplanner.data.vos.MealDishVO
import kotlinx.android.synthetic.main.view_item_daily.view.*

class DailyViewHolder(itemView : View, dayItemController: DayItemController) : BaseViewHolder<MealDishVO>(itemView) {

    private var mDayItemController : DayItemController? = null

    init {
        mDayItemController = dayItemController
    }

    override fun setData(data: MealDishVO) {
        mData = data

        itemView.tv_day_title.text = "Day "+data.day

        Glide.with(itemView.context)
            .load(data.picture!!)
            .into(itemView.iv_meal_bg)

        itemView.tv_num_of_meal.text = data.numOfMeals.toString()+ " meals included"
        itemView.tv_price.text = data.price.toString() + " kyats"
        itemView.tv_total_cal.text = "Total "+ data.totalCalories.toString()+ " calories"

    }

    override fun onClick(v: View?) {
        mDayItemController?.onTapDay(mData!!)
    }


}