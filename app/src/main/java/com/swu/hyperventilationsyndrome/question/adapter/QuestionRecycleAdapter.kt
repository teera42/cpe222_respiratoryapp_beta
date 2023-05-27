package com.swu.hyperventilationsyndrome.question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swu.hyperventilationsyndrome.databinding.ItemQuestionBinding

class QuestionRecycleAdapter : RecyclerView.Adapter<QuestionRecycleAdapter.ViewHolder>() {
    private var mSelection = mutableListOf<Int>()

    class ViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuestionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= mSelection.size - 1) {
            mSelection.add(-1)
        }

        holder.binding.quesRadioGroup.clearCheck()
        when (mSelection[position]) {
            0 -> {
                holder.binding.quesRadiobutton1.isChecked = true
            }

            1 -> {
                holder.binding.quesRadiobutton2.isChecked = true
            }

            2 -> {
                holder.binding.quesRadiobutton3.isChecked = true
            }

            3 -> {
                holder.binding.quesRadiobutton4.isChecked = true
            }
        }

        holder.binding.quesRadiobutton1.setOnClickListener {
            mSelection[position] = 0;
        }

        holder.binding.quesRadiobutton2.setOnClickListener {
            mSelection[position] = 1;
        }

        holder.binding.quesRadiobutton3.setOnClickListener {
            mSelection[position] = 2;
        }

        holder.binding.quesRadiobutton4.setOnClickListener {
            mSelection[position] = 3;
        }

    }
}

