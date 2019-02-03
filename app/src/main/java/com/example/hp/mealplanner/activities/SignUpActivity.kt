package com.example.hp.mealplanner.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.events.DataEvent
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SignUpActivity : AppCompatActivity() {

    var token : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_sign_up.setOnClickListener{

            var name = et_name.text.toString()
            var email = et_email.text.toString()
            var password = et_password.text.toString()

            MealPlannerModel.getInstance().registerUser(name, email, password)
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
    fun onRegisterLoadedEvent(registerEvent: DataEvent.OnSuccessRegisterEvent){
        token = registerEvent.token
        var intent = Intent(applicationContext, PhysicalProfileActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }
}