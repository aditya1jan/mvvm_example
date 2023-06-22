package com.nexxo.loginexample.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexxo.loginexample.R
import com.nexxo.loginexample.databinding.ListItemLayoutBinding
import com.nexxo.loginexample.home.model.HomeModel

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var d:List<HomeModel.AcquiringContractItem> = listOf()
    fun setData(acquiringContractItems: List<HomeModel.AcquiringContractItem>)
    {
        d = acquiringContractItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ListItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_layout,
        parent,
        false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listItemLayoutBinding.data = d[position]
    }

    override fun getItemCount(): Int {
        return d.size
    }
    inner class ViewHolder(val listItemLayoutBinding: ListItemLayoutBinding):RecyclerView.ViewHolder(listItemLayoutBinding.root) {
    }
}