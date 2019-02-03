package com.example.hp.mealplanner.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.hp.mealplanner.R
import kotlinx.android.synthetic.main.activity_physical_profile.*

class PhysicalProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical_profile)
        toolbar.title = "Physical Profile"
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        var token = intent.getStringExtra("token")

        btn_next.setOnClickListener {

            var height = et_inches.text.toString()
            var weight = et_weight.text.toString()
            var age = et_weight.text.toString()
            var gender = rg_gender.checkedRadioButtonId.toString()

            Log.e("height", height)
            Log.e("age", age)

            var intent = Intent(applicationContext, GoalProfileActivity::class.java)
            intent.putExtra("token", token)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("age", age)
            intent.putExtra("gender", gender)
            startActivity(intent)
        }

    }
}