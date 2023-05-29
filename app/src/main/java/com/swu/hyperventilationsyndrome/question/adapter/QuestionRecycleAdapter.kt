package com.swu.hyperventilationsyndrome.question.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.swu.hyperventilationsyndrome.R
import com.swu.hyperventilationsyndrome.dialog.UtilDialog
import com.swu.hyperventilationsyndrome.model.Question
import java.lang.Exception
import java.util.Collections

class QuestionRecycleAdapter(private val context: Context, private val data: Array<Question>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dialog: UtilDialog = UtilDialog(context)
    private var mSelection = mutableListOf<Int>()

    init {
        for (x in data.indices) {
            mSelection.add(0)
            Log.d("sasa",x.toString())
        }
    }

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
        val button: Button = view.findViewById(R.id.cy_button)
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

        when (holder.itemViewType) {
            1 -> {
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
                    mSelection[position] = 1
                }

                view.quesRadiobutton2.setOnClickListener {
                    mSelection[position] = 2
                }

                view.quesRadiobutton3.setOnClickListener {
                    mSelection[position] = 3
                }

                view.quesRadiobutton4.setOnClickListener {
                    mSelection[position] = 4
                }
            }

            2 -> {
                val view: ButtonViewHolder = holder as ButtonViewHolder

                view.button.setOnClickListener {

                    var inputAll: Boolean = false
                    val count: Int = Collections.frequency(mSelection, 0);
                    Toast.makeText(context, mSelection.size.toString(), Toast.LENGTH_LONG).show()
                    try {
                        if (count > 3) {
                            throw Exception("SelectionIncompleteException")
                        }
                        inputAll = true
                    } catch (_: Exception) {
                        dialog.dialogQuizError()
                    } finally {
                        if (inputAll) {
                            val mode = findMode(mSelection.toIntArray())
                            var maxCount = 0

                            for (num in mode) {
                                maxCount = maxOf(maxCount, num)
                            }

                            when (maxCount) {
                                1 -> dialog.dialogQuizInformation(
                                    R.drawable.ambulance1,
                                    "Good",
                                    1
                                )

                                2 -> dialog.dialogQuizInformation(
                                    R.drawable.ambulance2,
                                    "Careful",
                                    2
                                )

                                3 -> dialog.dialogQuizInformation(
                                    R.drawable.ambulance3,
                                    "Need to see a Doctor",
                                    3
                                )

                                4 -> dialog.dialogQuizInformation(
                                    R.drawable.ambulance4,
                                    "See a Doctor Now",
                                    4
                                )
                            }
                        }
                    }


                }

            }
        }

    }

    private fun findMode(array: IntArray): List<Int> {
        val list = array.toList()
        var maxCount = 0

        // หาความถี่สูงสุด
        for (num in list) {
            val count = list.count { it == num }
            maxCount = maxOf(maxCount, count)
        }

        val modes = mutableListOf<Int>()

        // หาค่าฐานนิยม
        for (num in list) {
            val count = list.count { it == num }
            if (count == maxCount && !modes.contains(num)) {
                modes.add(num)
            }
        }

        return modes
    }

}

