package com.example.hp.mealplanner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
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

        rg_goal.setOnCheckedChangeListener{ group, checkedId ->
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
    }
}