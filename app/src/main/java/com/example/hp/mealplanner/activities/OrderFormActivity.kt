package com.example.hp.mealplanner.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.data.model.MealPlannerModel
import com.example.hp.mealplanner.data.vos.MealDishVO
import com.example.hp.mealplanner.dialogs.OrderSuccessfulDialog
import com.example.hp.mealplanner.events.DataEvent
import kotlinx.android.synthetic.main.activity_order_form.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class OrderFormActivity : AppCompatActivity() {

    private var bfID: String = ""
    private var lunchID: String = ""
    private var dinnerID: String = ""
    private var totalPrice: Int = 0
    private var token: String = ""

    fun newIntent(context: Context, mealDishVO: MealDishVO): Intent {
        var intent = Intent(context, OrderFormActivity::class.java)

        var extra = Bundle()
        extra.putString("total_price", mealDishVO.price.toString())
        extra.putString("bf_id", mealDishVO.breakfastData!!.id)
        extra.putString("lunch_id", mealDishVO.lunchData!!.id)
        extra.putString("dinner_id", mealDishVO.dinnerData!!.id)

        intent.putExtras(extra)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_form)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.title = "Order Form"

        btn_order.setOnClickListener {
            var phoneNum = et_phone.text.toString()
            var address = et_address.text.toString()

            var bundle: Bundle = intent.extras
            if (bundle != null) {
                totalPrice = bundle.getString("total_price").toInt()
                bfID = bundle.getString("bf_id")
                lunchID = bundle.getString("lunch_id")
                dinnerID = bundle.getString("dinner_id")
            }

            val pref = PreferenceManager
                .getDefaultSharedPreferences(applicationContext)
            token = pref.getString("token", "anon")

            Log.e("iamtoken", token)

            MealPlannerModel.getInstance().createOrder(phoneNum, address, totalPrice, bfID, lunchID, dinnerID, token)
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
    fun onSuccessOrderEvent(orderEvent: DataEvent.OnSuccessOrder) {
        var reply = orderEvent.message
        if (reply == "success") {
            val alert: OrderSuccessfulDialog =
                OrderSuccessfulDialog()
            alert.showDialog(this, msg = R.string.order_successful)
            btn_order.isEnabled = false
            btn_go_back_home.visibility = View.VISIBLE
            btn_go_back_home.setOnClickListener {
                var intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}