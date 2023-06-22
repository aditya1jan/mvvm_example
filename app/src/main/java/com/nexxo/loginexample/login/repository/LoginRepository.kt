package com.nexxo.loginexample.login.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexxo.loginexample.api.ApiClient
import com.nexxo.loginexample.api.ApiInterface
import com.nexxo.loginexample.login.roomdb.LoginDatabase
import com.nexxo.loginexample.login.model.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.HeaderMap

class LoginRepository() {
    fun userLogin(params:HashMap<String,Any>,headers:HashMap<String,String>) : LiveData<LoginModel> {
        val loginResponse = MutableLiveData<LoginModel>()
        ApiClient.client.create(ApiInterface::class.java).userLogin(params,headers).enqueue(object : Callback<LoginModel> {
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {

                if (response.isSuccessful) {
                    loginResponse.value = response.body()
                } else {

                }
            }
        })

        return loginResponse
    }

}