package com.swu.hyperventilationsyndrome

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.swu.hyperventilationsyndrome.databinding.ActivityMainBinding
import com.swu.hyperventilationsyndrome.profile.ProfileActivity
import com.swu.hyperventilationsyndrome.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("App", MODE_PRIVATE)

        Handler(Looper.myLooper()!!)
            .postDelayed({
                val mIntent: Intent = if (sharedPreferences.getBoolean("isRegister", false)) {
                    Intent(this, ProfileActivity::class.java)
                } else {
                    Intent(this, RegisterActivity::class.java)
                }

                startActivity(mIntent)
                finish()
            }, 3000)
    }


}