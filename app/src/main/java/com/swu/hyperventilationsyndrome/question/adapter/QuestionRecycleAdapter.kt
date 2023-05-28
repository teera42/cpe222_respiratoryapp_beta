package com.swu.hyperventilationsyndrome.question.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swu.hyperventilationsyndrome.R
import com.swu.hyperventilationsyndrome.model.Question

class QuestionRecycleAdapter(private val data: Array<Question>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mSelection = mutableListOf<Int>()


    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleQues)
        val quesRadioGroup: RadioGroup = view.findViewById(R.id.quesRadioGroup);
        val quesRadiobutton1: RadioButton = view.findViewById(R.id.quesRadiobutton1);
        val quesRadiobutton2: RadioButton = view.findViewById(R.id.quesRadiobutton2);
        val quesRadiobutton3: RadioButton = view.findViewById(R.id.quesRadiobutton3);
        val quesRadiobutton4: RadioButton = view.findViewById(R.id.quesRadiobutton4);
    }

    inner class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        return when (viewType) {
            0 -> {
                view = inflater.inflate(R.layout.item_img, parent, false)
                ImageViewHolder(view)
            }

            1 -> {
                view = inflater.inflate(R.layout.item_question, parent, false)
                QuestionViewHolder(view)
            }

            else -> {
                view = inflater.inflate(R.layout.item_button, parent, false)
                ButtonViewHolder(view)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= mSelection.size - 1) {
            mSelection.add(0)
        }

        if (holder.itemViewType == 1) {

            val view: QuestionViewHolder = holder as QuestionViewHolder
            view.title.text = data[position].question
            view.quesRadiobutton1.text = data[position].choice[0]
            view.quesRadiobutton2.text = data[position].choice[1]
            view.quesRadiobutton3.text = data[position].choice[2]
            view.quesRadiobutton4.text = data[position].choice[3]
            holder.quesRadioGroup.clearCheck()
            when (mSelection[position]) {
                1 -> {
                    view.quesRadiobutton1.isChecked = true
                }

                2 -> {
                    view.quesRadiobutton2.isChecked = true
                }

                3 -> {
                    view.quesRadiobutton3.isChecked = true
                }

                4 -> {
                    view.quesRadiobutton4.isChecked = true
                }
            }

            view.quesRadiobutton1.setOnClickListener {
                mSelection[position] = 1;
            }

            view.quesRadiobutton2.setOnClickListener {
                mSelection[position] = 2;
            }

            view.quesRadiobutton3.setOnClickListener {
                mSelection[position] = 3;
            }

            view.quesRadiobutton4.setOnClickListener {
                mSelection[position] = 4;
            }

        }
    }
}

