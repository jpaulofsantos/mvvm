package com.example.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.RowImcBinding

class IMCAdapter: RecyclerView.Adapter<IMCViewHolder>() {

    private var listImc: List<IMCModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IMCViewHolder {
        val itemImc = RowImcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IMCViewHolder(itemImc)
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
}