package com.swu.hyperventilationsyndrome.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.swu.hyperventilationsyndrome.R
import com.swu.hyperventilationsyndrome.databinding.ActivityTrainingBinding
import com.swu.hyperventilationsyndrome.profile.ProfileActivity

class TrainingActivity : AppCompatActivity() {
    private var isPlay = false
    private lateinit var binding: ActivityTrainingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isPlay) {
            binding.buttonClick.setOnClickListener {
                isPlay = true
                ///sasas
                binding.buttonClick.setImageResource(R.drawable.no)
            }
        }

        binding.buttonClick.setOnLongClickListener {
            if (isPlay){
                startActivity(Intent(this,ProfileActivity::class.java))
                finish()
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (!isPlay) {
            finish()
        }

        isPlay = false
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show()
        Handler(Looper.myLooper()!!).postDelayed({
            isPlay = true
        }, 2000)
    }
}