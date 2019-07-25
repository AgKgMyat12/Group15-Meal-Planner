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

        Log.e("goal", goal)
        Log.e("gender", gender)
        Log.e("active", rg_active.checkedRadioButtonId.toString())

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
            var id = rg_active.checkedRadioButtonId
            var checkedRadio = rg_active.findViewById<View>(id) as RadioButton
            var active = checkedRadio.text.toString()

            var activeNum = when (active) {
                "Sedentary" -> 0
                "LightlyActive" -> 1
                "Moderately Active" -> 2
                "Very Active" -> 3
                else -> 4
            }

            MealPlannerModel.getInstance().createUserData(token, height.toInt(), weight.toInt(), age.toInt(),
                (gender.toInt())-1, goal.toInt(), activeNum)
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