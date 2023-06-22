package com.nexxo.loginexample.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexxo.loginexample.home.model.HomeModel
import com.nexxo.loginexample.home.repository.HomeRepository

class HomeViewModel: ViewModel() {
    private var  hitData = MutableLiveData<List<HomeModel>>()

    fun fetchDataFromServer(aToken:String):MutableLiveData<List<HomeModel>>
    {
        val token = HashMap<String,String>()
        token.put("Authorization", "Bearer $aToken")
        hitData = HomeRepository().getAllData(token)
        return hitData
    }
}