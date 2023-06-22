package com.nexxo.loginexample.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexxo.loginexample.R
import com.nexxo.loginexample.databinding.ListItemLayout2Binding
import com.nexxo.loginexample.home.model.HomeModel

class HomeAdapter2 : RecyclerView.Adapter<HomeAdapter2.ViewHolder>() {
    private var a:List<HomeModel.PaymentSettingItem> = listOf()
    fun setData(PaymentSettingItem: List<HomeModel.PaymentSettingItem>)
    {
        a = PaymentSettingItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemLayout2Binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_layout_2,
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(a[position].itemValue.isEmpty() || a[position].itemUnit.isEmpty()) {
            a.drop(position)
        }
        else
        {
            holder.listItemLayout2Binding.data2 = a[position]
        }
    }

    override fun getItemCount(): Int {
        return a.size
    }
    inner class ViewHolder(val listItemLayout2Binding: ListItemLayout2Binding):RecyclerView.ViewHolder(listItemLayout2Binding.root) {
    }
}