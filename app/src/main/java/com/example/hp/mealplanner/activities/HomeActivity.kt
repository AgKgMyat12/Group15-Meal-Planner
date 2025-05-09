package com.example.hp.mealplanner.activities

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.dialogs.UnderDevelopementDialog
import com.example.hp.mealplanner.fragments.DailyFragment
import kotlinx.android.synthetic.main.activity_home.*
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager


class HomeActivity : AppCompatActivity() {

    var token : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val logoID = R.mipmap.fit_food_white
        toolbar.setLogo(logoID)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigation.selectedItemId = R.id.item_meals


        token = intent.getStringExtra("token")

        val preferences = PreferenceManager
            .getDefaultSharedPreferences(applicationContext)
        preferences.edit().putString("token", token).apply()

        MealPlannerModel.getInstance().loadMeals(token)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.item_meals -> {
                val dailyMealsFragment = DailyFragment()
                openFragment(dailyMealsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.item_history -> {
                val alert : UnderDevelopementDialog =
                    UnderDevelopementDialog()
                alert.showDialog(this, msg = R.string.under_development)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }


}