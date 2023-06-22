package com.nexxo.loginexample.home.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexxo.loginexample.R
import com.nexxo.loginexample.databinding.ActivityHomeBinding
import com.nexxo.loginexample.home.adapter.HomeAdapter
import com.nexxo.loginexample.home.adapter.HomeAdapter2
import com.nexxo.loginexample.home.model.HomeModel
import com.nexxo.loginexample.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeAdapter2: HomeAdapter2
    private lateinit var mhomeViewModel: HomeViewModel
    private lateinit var homeActivityBinding: ActivityHomeBinding
    var token:String = ""
    private lateinit var data:List<HomeModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mhomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeActivityBinding.homeViewModel = mhomeViewModel
        homeActivityBinding.lifecycleOwner = this
        token = intent.getStringExtra("token").toString()
        initializeRecyclerView()
        initializeObservers()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initializeObservers() {
        mhomeViewModel.fetchDataFromServer(token).observe(this, Observer {
            if(!it.isEmpty())
            homeAdapter.setData(it[0].acquiringContractItems)
            data = it
        })
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
    }

    private fun initializeRecyclerView() {
        homeAdapter = HomeAdapter()
        homeAdapter2 = HomeAdapter2()
        homeActivityBinding.rvList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }



    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button1->{homeActivityBinding.rvList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = homeAdapter
            }
                homeAdapter.setData(data[0].acquiringContractItems)
            }
            R.id.button2->{
                homeActivityBinding.rvList.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = homeAdapter2
                }
                homeAdapter2.setData(data[0].paymentSettingItems)
            }
        }
    }

}