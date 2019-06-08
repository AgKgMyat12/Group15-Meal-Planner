package com.example.hp.mealplanner.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.Window
import com.example.hp.mealplanner.R
import com.example.hp.mealplanner.activities.HomeActivity
import kotlinx.android.synthetic.main.dialog_sucess_order.*

class OrderSuccessfulDialog {
    fun showDialog(activity: Activity, msg: Int){
        var dialog : Dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_sucess_order)

        dialog.tv_thanks.setText(msg)
        dialog.tv_agree_btn.text = "OK."
        dialog.tv_agree_btn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}