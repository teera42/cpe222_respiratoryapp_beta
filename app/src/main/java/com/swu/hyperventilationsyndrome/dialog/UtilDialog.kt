package com.swu.hyperventilationsyndrome.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.swu.hyperventilationsyndrome.R
import com.swu.hyperventilationsyndrome.profile.ProfileActivity
import com.swu.hyperventilationsyndrome.training.TrainingActivity

class UtilDialog(private val context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogLoading() {
        if (isShowing) dismiss()
        setContentView(R.layout.dialog_loanding)
        show()
    }

    fun dialogWarning( ) {
        if (isShowing) dismiss()
        setContentView(R.layout.dialog_warning)
        findViewById<Button>(R.id.again).setOnClickListener {
            dismiss()
        }
        show()
    }

    fun dialogInformation(ima: Int, message: String, cls: Class<*>) {
        if (isShowing) dismiss()
        setContentView(R.layout.dialog_information)
        findViewById<ImageView>(R.id.icon_dialog_information).setImageResource(ima)
        findViewById<TextView>(R.id.title_dialog_information).text = message

        findViewById<Button>(R.id.dialog_yse).setOnClickListener {
            context.startActivity(Intent(context, cls))
            (context as Activity).finish()
        }

        findViewById<Button>(R.id.dialog_no).setOnClickListener {
            context.startActivity(Intent(context, ProfileActivity::class.java))
            (context as Activity).finish()
        }

        show()

    }
}