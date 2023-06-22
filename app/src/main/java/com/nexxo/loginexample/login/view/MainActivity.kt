package com.nexxo.loginexample.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nexxo.loginexample.util.AuthListener
import com.nexxo.loginexample.login.viewmodel.LoginViewModel
import com.nexxo.loginexample.R
import com.nexxo.loginexample.util.Util

import com.nexxo.loginexample.databinding.ActivityMainBinding
import com.nexxo.loginexample.home.view.HomeActivity
import com.nexxo.loginexample.login.model.LoginModel

class MainActivity : AppCompatActivity(), AuthListener {

    val util: Util = Util()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding? = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding?.loginViewModel = viewModel
        viewModel.authListener = this

    }
    override fun onStarted() {
        //util.myToast(this,"Login Started")
    }

    override fun onSuccess(loginResponseFromUserRepository: LiveData<LoginModel>) {
        loginResponseFromUserRepository.observe(this, Observer
        {
            util.myToast(this,"Login")
            var intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("token",it.accessToken)
            startActivity(intent)
        })
    }

    override fun onFailure(message: String) {
        util.myToast(this,message)
    }
}