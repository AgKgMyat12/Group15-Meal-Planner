package com.example.hp.mealplanner.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.hp.mealplanner.data.vos.MealDishVO
import kotlinx.android.synthetic.main.view_item_daily_meals.view.*

class DailyMealsViewHolders(itemView : View) : BaseViewHolder<MealDishVO>(itemView) {


    override fun setData(data: MealDishVO) {
        mData = data

        itemView.tv_day.text = "Day "+data.day

        Glide.with(itemView.context)
            .load(data.breakfastData!!.photo)
            .into(itemView.iv_meal_pic_bf)

        itemView.tv_meal_name_bf.text = data.breakfastData!!.name
        itemView.tv_grams_bf.text = data.breakfastData!!.breakfastGram.toString() + " grams"

        Glide.with(itemView.context)
            .load(data.lunchData!!.photo)
            .into(itemView.iv_meal_pic_lunch)

        itemView.tv_meal_name_lunch.text = data.lunchData!!.name
        itemView.tv_grams_lunch.text = data.lunchData!!.lunchGram.toString() + " grams"

        Glide.with(itemView.context)
            .load(data.dinnerData!!.photo)
            .into(itemView.iv_meal_pic_dinner)

        itemView.tv_meal_name_dinner.text = data.dinnerData!!.name
        itemView.tv_grams_dinner.text = data.dinnerData!!.dinnerGram.toString()+ " grams"

    }

    override fun onClick(v: View?) {

    }


}