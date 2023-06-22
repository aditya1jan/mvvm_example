package com.nexxo.loginexample.home.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nexxo.loginexample.api.ApiClient
import com.nexxo.loginexample.api.ApiInterface
import com.nexxo.loginexample.home.model.HomeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository() {

    fun getAllData(headers: HashMap<String,String>):MutableLiveData<List<HomeModel>>
    {
        var hitData = MutableLiveData<List<HomeModel>>()
        ApiClient.client.create(ApiInterface::class.java).getData(headers).enqueue(object :Callback<List<HomeModel>>{
            override fun onResponse(call: Call<List<HomeModel>>, response: Response<List<HomeModel>>) {
                if (response.isSuccessful)
                    hitData.value = response.body()
                else
                    Log.e("error", response.errorBody().toString())
            }

            override fun onFailure(call: Call<List<HomeModel>>, t: Throwable) {
                Log.e("error", t.message.toString())
            }

        })
        return hitData
    }
}