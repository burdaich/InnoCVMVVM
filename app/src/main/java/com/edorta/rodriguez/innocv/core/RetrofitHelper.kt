package com.edorta.rodriguez.innocv.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hello-world.innocv.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}