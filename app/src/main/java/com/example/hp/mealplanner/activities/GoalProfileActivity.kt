package com.example.hp.mealplanner.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import com.example.hp.mealplanner.R
import kotlinx.android.synthetic.main.activity_goal_profile.*

class GoalProfileActivity : AppCompatActivity() {

    private var mLastCheckedRB: CompoundButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_profile)
        toolbar.title = "Goal"
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        var token = intent.getStringExtra("token")
        var height = intent.getStringExtra("height")
        var weight = intent.getStringExtra("weight")
        var age = intent.getStringExtra("age")
        var gender = intent.getStringExtra("gender")

        rg_goal.setOnCheckedChangeListener { group, checkedId ->
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

        btn_next.setOnClickListener {
            var intent = Intent(applicationContext, DailyActiveActivity::class.java)
            intent.putExtra("token", token)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("age", age)
            intent.putExtra("gender", gender)
            intent.putExtra("goal", rg_goal.checkedRadioButtonId.toString())
            startActivity(intent)
        }
    }
}