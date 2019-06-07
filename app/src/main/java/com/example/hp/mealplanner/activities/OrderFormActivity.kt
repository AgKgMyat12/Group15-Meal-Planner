package com.example.hp.mealplanner.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hp.mealplanner.R
import kotlinx.android.synthetic.main.activity_order_form.*

class OrderFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_form)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.title = "Order Form"
    }
}