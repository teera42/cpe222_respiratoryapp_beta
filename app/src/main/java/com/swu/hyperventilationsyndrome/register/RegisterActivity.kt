package com.swu.hyperventilationsyndrome.register

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.swu.hyperventilationsyndrome.R
import com.swu.hyperventilationsyndrome.databinding.ActivityRegisterBinding
import com.swu.hyperventilationsyndrome.dialog.UtilDialog
import com.swu.hyperventilationsyndrome.math.HyperMath
import com.swu.hyperventilationsyndrome.model.DataModel
import com.swu.hyperventilationsyndrome.profile.ProfileActivity
import com.swu.hyperventilationsyndrome.repository.DataRepository
import com.swu.hyperventilationsyndrome.retrofit.GetApi
import com.swu.hyperventilationsyndrome.training.TrainingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dialog: UtilDialog
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        dialog = UtilDialog(this)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("App", MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        val resource = resources.getStringArray(R.array.sex)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, resource)

        binding.spinner.adapter = adapter

        binding.confirm.setOnClickListener {
            if (binding.name.text.trim().isNotEmpty() && binding.age.text.trim() .isNotEmpty() && binding.spinner.selectedItem.toString() != "Select Sec" ) {
                sharedPreferences.edit().let {
                    it.putString("name", binding.name.text.toString())
                    it.putInt("age", Integer.valueOf(binding.age.text.toString()))
                    it.putString("sex", binding.spinner.selectedItem.toString())
                    it.putBoolean("isRegister", true);
                    it.apply()
                }
                dialog.dialogLoading()
                getApi()
            } else {
                dialog.dialogWarning()
            }
        }
    }

    private fun getApi() {
        val http = GetApi.getRetrofit()

        val dataRepository = http.create(DataRepository::class.java)
        val call: Call<List<DataModel>> = dataRepository.getData()

        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                if (response.isSuccessful) {
                    val data: List<DataModel>? = response.body()
                    val math = HyperMath(data)

                    var isFrequency: Boolean = false
                    val frequency = math.frequency()

                    Log.d("test",frequency.toString() + " | " + math.amplitude().toString())
                    when (sharedPreferences.getInt("age", 0)) {
                        //30-40
                        in 0..1 -> {
                            if (frequency in 30.0..40.0) isFrequency = true
                        }
                        //20-29
                        in 1..5 -> {
                            if (frequency in 20.0..30.0) isFrequency = true
                        }

                        in 6..12 -> {
                            if (frequency in 15.0..25.0) isFrequency = true
                        }

                        else ->  isFrequency = true
                    }

                    if ((isFrequency && math.amplitude() < 5.0)) {
                        dialog.dialogInformation(R.drawable.accept,"Normal",ProfileActivity::class.java)
                    } else {
                        dialog.dialogInformation(R.drawable.warning,"Warning",TrainingActivity::class.java)
                    }

                } else {
                    dialog.dialogWarning()
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                dialog.dialogWarning()
            }

        })
    }

}