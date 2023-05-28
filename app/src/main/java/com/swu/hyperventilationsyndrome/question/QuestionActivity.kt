package com.swu.hyperventilationsyndrome.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swu.hyperventilationsyndrome.databinding.ActivityQuestionBinding
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.swu.hyperventilationsyndrome.model.Question
import com.swu.hyperventilationsyndrome.question.adapter.QuestionRecycleAdapter


import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonString = readJsonFromAsset(this, "question.json").trim()

        val gson = Gson()
        val questions: Array<Question> = gson.fromJson(jsonString, Array<Question>::class.java)

//        for (question in questions) {
//            Log.d("testChoice",question.question)
//            if (question.choice.isNotEmpty()) {
//                println("Choices:")
//                for (choice in question.choice) {
//                    Log.d("testChoice",choice)
//                }
//            } else {
//                Log.d("testChoice","No choices available for this question")
//            }
//        }


        binding.question.layoutManager = LinearLayoutManager(this)
        binding.question.adapter = QuestionRecycleAdapter(questions)
    }


    private fun readJsonFromAsset(context: Context, fileName: String): String {
        var json: String = ""
        try {
            val inputStream = context.assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = stringBuilder.toString()
            bufferedReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}