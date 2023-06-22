package com.nexxo.loginexample.login.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nexxo.loginexample.login.model.LoginModel

@Dao
interface LoginDao {

    @Insert
    fun insert(loginModel: LoginModel)

    @Update
    fun update(loginModel: LoginModel)

    @Delete
    fun delete(loginModel: LoginModel)

    @Query("select * from login ORDER BY id DESC")
    fun getTokenData(): LiveData<List<LoginModel>>
}