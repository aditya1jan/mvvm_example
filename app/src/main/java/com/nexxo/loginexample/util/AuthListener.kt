package com.nexxo.loginexample.util

import androidx.lifecycle.LiveData
import com.nexxo.loginexample.login.model.LoginModel

interface AuthListener {
    // this method is use for when login operation started
    fun onStarted()
    // this method is use for when login operation Success
    fun onSuccess(loginResponseFromUserRepository: LiveData<LoginModel>)
    // this method is use for when login operation failure
    fun onFailure(message:String)
}