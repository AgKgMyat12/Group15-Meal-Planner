package com.example.hp.mealplanner.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.events.DataEvent

import kotlinx.android.synthetic.main.activity_log_in.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LogInActivity : AppCompatActivity() {

    var token : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        tv_sign_up.setOnClickListener {
            var intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)

        }

        btn_login.setOnClickListener {
            var username = et_username.text.toString()
            var password = et_password.text.toString()

            MealPlannerModel.getInstance().loginUser(username, password)
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
    fun onLoginLoadedEvent(loginEvent: DataEvent.OnSuccessLoginEvent){
        token = loginEvent.token
        var intent = Intent(applicationContext, HomeActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
