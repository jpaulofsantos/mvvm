package com.example.mvvm.view.viewholder

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        bind.textId.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de registro")
                .setMessage("Deseja remover o ID: ${imcModel.id}?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(imcModel.id)
                    Toast.makeText(itemView.context, "Registro ID ${imcModel.id} removido!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Não", null)
                .create().show()
            true
        }
    }
}