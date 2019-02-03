package com.example.hp.mealplanner.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.adapters.DailyMealsAdapter
import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.events.DataEvent
import kotlinx.android.synthetic.main.fragment_daily_meals.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList

class DailyMealsFragment : Fragment() {

    private var mDailyMealsAdapter : DailyMealsAdapter? = null
    private var mDishList : List<MealDishVO> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_daily_meals, container, false)

        mDailyMealsAdapter = DailyMealsAdapter(this.context!!)
        view.srv_daily_meals.adapter = mDailyMealsAdapter
        var linearLayoutManger: LinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        view.srv_daily_meals.layoutManager = linearLayoutManger

        return view
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
    fun onMealLoaded(mealLoadedEvent: DataEvent.OnSuccessLoadMeal){
        mDishList = mealLoadedEvent.dishes
        mDailyMealsAdapter!!.appendNewData(mDishList)
    }


}