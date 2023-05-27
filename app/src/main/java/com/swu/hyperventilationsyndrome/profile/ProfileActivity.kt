package com.swu.hyperventilationsyndrome.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.swu.hyperventilationsyndrome.databinding.ActivityProfileBinding
import com.swu.hyperventilationsyndrome.training.TrainingActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding;
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences("App", MODE_PRIVATE)
        setContentView(binding.root)

        binding.train.setOnClickListener {
            startActivity(Intent(this, TrainingActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        binding.dateProgress.text = "${sharedPreferences.getInt("progress", 0)}/15"
        binding.username.text = sharedPreferences.getString("name", "none")
        binding.age.text = sharedPreferences.getInt("age", 0).toString()
        binding.sex.text = sharedPreferences.getString("sex", "non select")

        when (sharedPreferences.getInt("fail", 3)) {
            1 -> {
                binding.star1.visibility = View.VISIBLE
            }

            2 -> {
                binding.star1.visibility = View.VISIBLE
                binding.star2.visibility = View.VISIBLE
            }

            3 -> {
                binding.star1.visibility = View.VISIBLE
                binding.star2.visibility = View.VISIBLE
                binding.star3.visibility = View.VISIBLE
            }

            else -> {

            }
        }
    }

    private var isExit = false

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (isExit) {
            finish()
        }

        isExit = true
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show()
        Handler(Looper.myLooper()!!).postDelayed({
            isExit = false
        }, 2000)
    }
}