package com.nexxo.loginexample.api

import com.nexxo.loginexample.home.model.HomeModel
import com.nexxo.loginexample.login.model.LoginModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("passive/token")
    fun userLogin(@Body params:HashMap<String,Any>, @HeaderMap headers:HashMap<String,String>) : Call<LoginModel>

    @GET("bo/contract/details/FCFCEB281C604E52?productList=10")
    fun getData(@HeaderMap headers: HashMap<String, String>) : Call<List<HomeModel>>
}