package com.swu.hyperventilationsyndrome.repository

import com.swu.hyperventilationsyndrome.model.DataModel
import retrofit2.Call
import retrofit2.http.GET


interface DataRepository {
    @GET("exec")
    fun getData() : Call<List<DataModel>>
}