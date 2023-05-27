package com.swu.hyperventilationsyndrome.training

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.swu.hyperventilationsyndrome.R
import com.swu.hyperventilationsyndrome.databinding.ActivityTrainingBinding
import com.swu.hyperventilationsyndrome.profile.ProfileActivity
import java.text.SimpleDateFormat
import java.util.Date

class TrainingActivity : AppCompatActivity() {
    val time = 15 * 60 * 1000L
    private var isPlay = false
    private lateinit var binding: ActivityTrainingBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        binding.progress.max = time.toInt()
        binding.progress.progress = time.toInt()
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("App", MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        val countDownTimer = object : CountDownTimer(time, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {

                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60

                binding.showtime.text = "$min:$sec"
                binding.progress.progress = millisUntilFinished.toInt()
            }

            @SuppressLint("SimpleDateFormat", "CommitPrefEdits")
            override fun onFinish() {
                binding.showtime.text = "0:0"

                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val date = Date()
                val current = formatter.format(date)

                if (sharedPreferences.getString("date", "") != current) {
                    sharedPreferences.edit().apply {
                        putInt("progress", sharedPreferences.getInt("progress", 0) + 1)
                        putString("date", current)
                        apply()
                    }
                }
                finish()
            }
        }

        binding.buttonClick.setOnClickListener {
            if (!isPlay) {
                isPlay = true
                countDownTimer.start()
                binding.buttonClick.setImageResource(R.drawable.no)
            }
        }

        binding.buttonClick.setOnLongClickListener {
            if (isPlay) {
                startActivity(Intent(this, ProfileActivity::class.java))
                countDownTimer.cancel()
                finish()
            }
            true
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (!isPlay) {
            finish()
        } else {
            isPlay = false
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show()
        }


        Handler(Looper.myLooper()!!).postDelayed({
            isPlay = true
        }, 2000)
    }
}