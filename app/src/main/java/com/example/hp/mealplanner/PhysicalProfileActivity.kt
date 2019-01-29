package com.example.hp.mealplanner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_physical_profile.*

class PhysicalProfileActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical_profile)
        toolbar.title = "Physical Profile"
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}