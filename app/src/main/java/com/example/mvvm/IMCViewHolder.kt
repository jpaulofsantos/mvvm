package com.example.mvvm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.RowImcBinding

class IMCViewHolder(private val bind: RowImcBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(imcModel: IMCModel) {
        //val string = "Peso: ${imcModel.peso} - Altura: ${imcModel.altura} - IMC Calculado: ${imcModel.imc} "

        bind.textId.text = "Registro: ${imcModel.id}"
        bind.textAltura.text = "Altura: ${imcModel.altura}"
        bind.textPeso.text = "Peso: ${imcModel.peso}"
        bind.textImc.text = "IMC calculado: ${imcModel.imc}"
    }
}