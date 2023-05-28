package com.swu.hyperventilationsyndrome.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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

    fun dialogWarning() {
        if (isShowing) dismiss()
        setContentView(R.layout.dialog_warning)
        findViewById<Button>(R.id.again).setOnClickListener {
            dismiss()
        }
        show()
    }

    fun dialogQuizError() {
        if (isShowing) dismiss()
        setContentView(R.layout.dialog_quiz_error)
        findViewById<Button>(R.id.ok).setOnClickListener {
            dismiss()
        }
        show()
    }

    fun dialogQuizInformation(ima: Int, message: String, range: Int) {
        if (isShowing) dismiss()
        setContentView(R.layout.dialog_quiz_information)
        findViewById<ImageView>(R.id.icon).setImageResource(ima)
        findViewById<TextView>(R.id.title_dialog_quiz_information).text = message
        val butt = findViewById<Button>(R.id.confirm_quiz)

        butt.apply {
            val color = when (range) {
                1 -> Color.GREEN
                2 -> Color.YELLOW
                3 -> Color.rgb(255, 165, 0)
                else -> Color.RED
            }
            backgroundTintList = ColorStateList.valueOf(color)
            setOnClickListener {
                (context as Activity).finish()
            }
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