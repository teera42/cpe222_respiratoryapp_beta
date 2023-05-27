package com.swu.hyperventilationsyndrome.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.swu.hyperventilationsyndrome.databinding.ActivityQuestionBinding
import com.swu.hyperventilationsyndrome.question.adapter.QuestionRecycleAdapter

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.question.layoutManager = LinearLayoutManager(this)
        binding.question.adapter = QuestionRecycleAdapter()
    }
}