package com.swu.hyperventilationsyndrome.profile

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.swu.hyperventilationsyndrome.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding;
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences("App", MODE_PRIVATE)
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        binding.dateProgress.text = "${sharedPreferences.getInt("progress", 0)}/15"
        binding.username.text = sharedPreferences.getString("name","none")
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
}