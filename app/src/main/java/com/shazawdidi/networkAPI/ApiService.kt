package com.example.newsadroidapp.NetworkAPI

import com.shazawdidi.pojoClasses.DataClass
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("everything")
    fun getNewsList(
        @Query("q") q: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") key: String?
    ): retrofit2.Call<DataClass>


    }