package com.shazawdidi.newsViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shazawdidi.repository.NewsRepo
import com.shazawdidi.pojoClasses.DataClass
import com.shazawdidi.utils.RetrofitClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel : ViewModel() {

    private var repository:NewsRepo
    private var mNewsList = MutableLiveData<DataClass>()
    private val isDataLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    // get Data from API when ini ViewModel
    init {
         repository  = NewsRepo()
        mNewsList = repository.getList()
    }


    // return current List
    fun getNewList(): MutableLiveData<DataClass> {
        return mNewsList
    }


    fun getIsLoading() : MutableLiveData<Boolean>{
        return repository.getIsLoading()
    }



}

