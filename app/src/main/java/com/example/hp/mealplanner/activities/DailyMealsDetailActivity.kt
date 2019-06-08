package com.example.hp.mealplanner.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.data.vos.MealDishVO
import kotlinx.android.synthetic.main.activity_daily_meals_detail.*

class DailyMealsDetailActivity : AppCompatActivity() {

    private var mTapDay : MealDishVO? = null
    private var id : String = ""
    private var mOrderFormActivity = OrderFormActivity()

    fun newIntent(context: Context, mealDishVO: MealDishVO) : Intent{
        var intent = Intent(context, DailyMealsDetailActivity::class.java)

        var extra = Bundle()
        extra.putString("user_tap_day", mealDishVO.id)
        intent.putExtras(extra)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_meals_detail)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        var bundle: Bundle = intent.extras
        if (bundle != null) {
            id =bundle.getString("user_tap_day")
        }
        mTapDay = MealPlannerModel.getInstance().getDayID(id)
        bindMeal(mTapDay!!)

        btn_order.setOnClickListener {
            var intent = mOrderFormActivity.newIntent(applicationContext, mTapDay!!)
            startActivity(intent)

        }

    }

    private fun bindMeal(mTapDay: MealDishVO) {
        toolbar.title = "Day "+ mTapDay!!.day
        Glide.with(applicationContext)
            .load(mTapDay.breakfastData!!.photo)
            .into(iv_meal_pic_bf)

        tv_meal_name_bf.text = mTapDay.breakfastData!!.name
        tv_grams_bf.text = mTapDay.breakfastData!!.breakfastGram.toString() + " grams"
        tv_cal_bf.text = mTapDay.breakfastData!!.calories.toString() + " calories"
        tv_ingredient_bf.text = mTapDay.breakfastData!!.ingredients

        Glide.with(applicationContext)
            .load(mTapDay.lunchData!!.photo)
            .into(iv_meal_pic_lunch)

        tv_meal_name_lunch.text = mTapDay.lunchData!!.name
        tv_grams_lunch.text = mTapDay.lunchData!!.lunchGram.toString() + " grams"
        tv_cal_lunch.text = mTapDay.lunchData!!.calories.toString() + " calories"
        tv_ingredient_lunch.text = mTapDay.lunchData!!.ingredients

        Glide.with(applicationContext)
            .load(mTapDay.dinnerData!!.photo)
            .into(iv_meal_pic_dinner)

        tv_meal_name_dinner.text = mTapDay.dinnerData!!.name
        tv_grams_dinner.text = mTapDay.dinnerData!!.dinnerGram.toString()+ " grams"
        tv_cal_dinner.text = mTapDay.dinnerData!!.calories.toString() + " calories"
        tv_ingredient_dinner.text = mTapDay.dinnerData!!.ingredients
    }
}