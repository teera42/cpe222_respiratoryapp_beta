package com.swu.hyperventilationsyndrome.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetApi {

    private var retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl("https://script.google.com/macros/s/AKfycbyaor-o2kRdUZv27tWAr1b_MFH1u1A6DwRKc2ENaqNOVzcGfivFrIwaTMQSxiGmRRl_/")
        .build()

     fun getRetrofit() : Retrofit{
        return retrofit
    }
}