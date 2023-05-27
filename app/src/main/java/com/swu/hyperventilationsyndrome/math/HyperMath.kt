package com.swu.hyperventilationsyndrome.math

import com.swu.hyperventilationsyndrome.model.DataModel


class HyperMath(lis: List<DataModel>?) {
    private var amplitudePositive: ArrayList<Int> = ArrayList()
    private var amplitudeNegative: ArrayList<Int> = ArrayList()
    private var frequency: Int = 0

    init {
        if (lis != null) {
            for (data in lis) {
                amplitudePositive.add(data.an_p)
                amplitudeNegative.add(data.ap_p)
                frequency += data.frequency
            }
            frequency /= lis.size
        }


    }

    fun amplitude(): Double {
        var slope: Double = 0.0
        for (i in 1 until amplitudePositive.size) {
            val difference = amplitudePositive[i] - amplitudePositive[i - 1]
            slope += difference.toDouble()
        }

        slope /= amplitudePositive.size.toDouble()
        return slope
    }

    fun frequency(): Int {
        return frequency * 60
    }
}