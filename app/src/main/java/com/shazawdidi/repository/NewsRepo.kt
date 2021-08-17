package com.shazawdidi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsadroidapp.NetworkAPI.ApiService
import com.shazawdidi.utils.RetrofitClass
import com.shazawdidi.pojoClasses.DataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepo {

    lateinit var apiService :ApiService;
    private val list: MutableLiveData<DataClass> = MutableLiveData<DataClass>()
    private val isDataLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    //  ini and prepare data for send
    //https://newsapi.org/v2/everything?q=tesla&from=2021-07-17&sortBy=publishedAt&apiKey=b0b1dce503684ca19f410f609d9aa536
    var apiKey :String ="b0b1dce503684ca19f410f609d9aa536"
    var from :String ="2021-07-17"
    var sortBy :String ="publishedAt"
    var q :String ="tesla"



    // return list data comes from End point
    fun getList(): MutableLiveData<DataClass> {

        isDataLoading.value = true ;

        apiService = RetrofitClass.getInstance() .create(ApiService::class.java)
        apiService.getNewsList(q, from, sortBy, apiKey).enqueue(object : Callback<DataClass> {

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {

                isDataLoading.value = false ;

                // show Data on LogCat
                Log.e("TAG", "Response  is => " + response.isSuccessful)
                Log.e("TAG", "Response  is => " + response.code())
                Log.e("TAG", "Response  is => " + response.body())
                Log.e("TAG", "Response  is => " + response.isSuccessful)

                if (response.isSuccessful) {
                    list.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                Log.e("TAG", "Response if Error  => " + t.message)
                list.postValue(null)
            }
        })

        return list
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return isDataLoading ;
    }

}

