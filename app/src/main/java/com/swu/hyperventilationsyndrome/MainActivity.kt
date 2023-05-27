package com.swu.hyperventilationsyndrome

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.swu.hyperventilationsyndrome.databinding.ActivityMainBinding
import com.swu.hyperventilationsyndrome.question.QuestionActivity
import com.swu.hyperventilationsyndrome.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("App", MODE_PRIVATE)
        Handler(Looper.myLooper()!!)
            .postDelayed({
                val mIntent: Intent = if (!sharedPreferences.getBoolean("isRegister", false)) {
                    Intent(this, QuestionActivity::class.java)
                } else {
                    Intent(this, RegisterActivity::class.java)
                }

                startActivity(mIntent)
                finish()
            }, 3000)
    }
}