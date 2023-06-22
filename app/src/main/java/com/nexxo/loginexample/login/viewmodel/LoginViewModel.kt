package com.nexxo.loginexample.login.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.nexxo.loginexample.login.repository.LoginRepository
import com.nexxo.loginexample.util.AuthListener
import com.nexxo.loginexample.util.Util


class LoginViewModel():ViewModel() {


    var number: String? = ""
    var password: String? = ""
    var authListener: AuthListener? = null
    private val util: Util = Util()

    fun onLoginButtonClick(view: View)
    {
        authListener?.onStarted()
        if (number.isNullOrEmpty())
        {
            authListener?.onFailure("Please enter Mobile number")
            return
        }
        else if(number!!.length<10 || number!!.length>10)
        {
            authListener?.onFailure("Please enter valid Mobile number")
            return
        }
        else if(password.isNullOrEmpty())
        {

            authListener?.onFailure("Please enter password")
            return
        }
        else if(password!!.length<6)
        {
            authListener?.onFailure("Please enter atleast 6 digit password")
            return
        }
        else if(password!!.length>10)
        {
            authListener?.onFailure("Password can not be greater than 10 digit")
            return
        }
        else
        {
            val params = HashMap<String,Any>()
            params["mobileOrEmail"] = number.toString()
            params["password"] = password.toString()
            params["deviceDetails"] = util.deviceDetails()
            val headers = HashMap<String,String>()
            headers["Content-Type"] = "application/json"
            headers["Accept-Language"] = "en"
            headers["User-Agent"] = "agent"
            headers["X-AppId"] = "9361528067E426E8"
            headers["X-Channel"] = "android"
            val loginResponseFromUserRepository = LoginRepository().userLogin(params,headers)
            authListener?.onSuccess(loginResponseFromUserRepository)

        }

    }
}