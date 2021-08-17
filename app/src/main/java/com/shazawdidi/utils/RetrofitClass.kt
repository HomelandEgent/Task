package com.shazawdidi.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {
    companion object {  // used as static function

        // the base url
        private var baseURl :String = "https://newsapi.org/v2/";
        private var retrofit: Retrofit? = null

        fun getInstance() : Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    }
}