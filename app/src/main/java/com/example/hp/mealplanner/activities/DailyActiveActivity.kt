package com.example.hp.mealplanner.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.events.DataEvent
import kotlinx.android.synthetic.main.activity_daily_active.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DailyActiveActivity : AppCompatActivity() {

    private var mLastCheckedRB: CompoundButton? = null
    var message : String = ""
    var token : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_active)
        toolbar.title = "Activity Level"
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        token = intent.getStringExtra("token")
        var height = intent.getStringExtra("height")
        var weight = intent.getStringExtra("weight")
        var age = intent.getStringExtra("age")
        var gender = intent.getStringExtra("gender")
        var goal = intent.getStringExtra("goal")

        rg_active.setOnCheckedChangeListener{ group, checkedId ->
            var greenBorder = R.drawable.bg_radio_button_green
            var greyBorder = R.drawable.bg_radio_button_grey

            val checkedRb = group.findViewById<View>(checkedId) as RadioButton

            if (mLastCheckedRB != null) {
                mLastCheckedRB!!.isChecked = false
                mLastCheckedRB!!.setBackgroundResource(greyBorder)
            }
            //store the clicked radiobutton
            mLastCheckedRB = checkedRb
            mLastCheckedRB!!.setBackgroundResource(greenBorder)
        }

        btn_done.setOnClickListener {
            MealPlannerModel.getInstance().createUserData(token, height.toInt(), weight.toInt(), age.toInt(),
                gender.toInt(), goal.toInt(), rg_active.checkedRadioButtonId)
        }

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
    fun onCreateUserDataEvent(createUserDataEvent: DataEvent.OnSuccessCreateUserDataEvent){
        message = createUserDataEvent.message
        if (message == "success")
        {
            var intent = Intent(applicationContext, HomeActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }

    }
}