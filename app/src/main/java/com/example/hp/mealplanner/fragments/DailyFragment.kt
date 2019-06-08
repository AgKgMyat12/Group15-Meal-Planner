package com.example.hp.mealplanner.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.activities.DailyMealsDetailActivity
import com.example.hp.mealplanner.adapters.DailyAdapter
import com.example.hp.mealplanner.controller.DayItemController
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.events.DataEvent
import kotlinx.android.synthetic.main.fragment_daily_meals.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList

class DailyFragment : Fragment(), DayItemController {

    private var mDailyAdapter: DailyAdapter? = null
    private var mDishList: List<MealDishVO> = ArrayList()
    private var mDailyMealsDetailActivity = DailyMealsDetailActivity()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_daily_meals, container, false)

        mDailyAdapter = DailyAdapter(this.context!!, this)
        view.srv_daily_meals.adapter = mDailyAdapter
        var linearLayoutManger: LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.srv_daily_meals.layoutManager = linearLayoutManger

        mDishList = MealPlannerModel.getInstance().getMeal()
        mDailyAdapter!!.appendNewData(mDishList)

        return view
    }

    override fun onTapDay(mealDishVO: MealDishVO) {
        var intent = mDailyMealsDetailActivity.newIntent(context!!, mealDishVO)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMealLoaded(mealLoadedEvent: DataEvent.OnSuccessLoadMeal) {
        mDishList = mealLoadedEvent.dishes
        mDailyAdapter!!.appendNewData(mDishList)
    }


}