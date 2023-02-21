package com.example.mvvm.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.view.listener.OnIMCListener
import com.example.mvvm.databinding.RowImcBinding
import com.example.mvvm.model.IMCModel

class IMCViewHolder(private val bind: RowImcBinding, private val listener: OnIMCListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(imcModel: IMCModel) {
        //val string = "Peso: ${imcModel.peso} - Altura: ${imcModel.altura} - IMC Calculado: ${imcModel.imc} "

        bind.textId.text = "Registro: ${imcModel.id}"
        bind.textAltura.text = "Altura: ${imcModel.altura}"
        bind.textPeso.text = "Peso: ${imcModel.peso}"
        bind.textImc.text = "IMC calculado: ${imcModel.imc}"

        bind.textId.setOnClickListener {
            listener.onClick(imcModel.id)
        }
    }
}