package com.braula.abaxapp.model.service

import com.braula.abaxapp.model.service.repository.BeerApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_API_URL = "https://api.punkapi.com"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private fun retrofitPlayfab(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val beerApi: BeerApi = retrofitPlayfab().create(BeerApi::class.java)
}