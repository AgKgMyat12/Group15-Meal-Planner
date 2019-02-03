package com.example.hp.mealplanner.dialogs

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.example.hp.mealplanner.R
import kotlinx.android.synthetic.main.simple_dialog_box.*

class UnderDevelopementDialog {
    fun showDialog(activity: Activity, msg: Int){
        var dialog : Dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.simple_dialog_box)

        dialog.tv_under_development.setText(msg)
        dialog.tv_agree_btn.text = "OK"
        dialog.tv_agree_btn.setOnClickListener {
            dialog.dismiss() }

        dialog.show()
    }
}