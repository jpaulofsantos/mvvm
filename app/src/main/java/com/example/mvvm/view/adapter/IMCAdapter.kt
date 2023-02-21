package com.example.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.view.listener.OnIMCListener
import com.example.mvvm.databinding.RowImcBinding
import com.example.mvvm.model.IMCModel
import com.example.mvvm.view.viewholder.IMCViewHolder

class IMCAdapter: RecyclerView.Adapter<IMCViewHolder>() {

    private var listImc: List<IMCModel> = listOf()
    private lateinit var listener: OnIMCListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IMCViewHolder {
        val itemImc = RowImcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IMCViewHolder(itemImc, listener)
    }

    override fun onBindViewHolder(holder: IMCViewHolder, position: Int) {
        holder.bind(listImc[position])
    }

    override fun getItemCount(): Int {
        return listImc.count()
    }

    fun updateImc(list: List<IMCModel>) {
        listImc = list
        notifyDataSetChanged()
    }

    fun getListener(listenerFrom: OnIMCListener) {
        listener = listenerFrom
    }
}