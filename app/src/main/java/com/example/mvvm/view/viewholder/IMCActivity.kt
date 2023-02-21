package com.example.mvvm.view.viewholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityImcactivityBinding
import com.example.mvvm.model.IMCModel
import com.example.mvvm.view.adapter.IMCAdapter
import com.example.mvvm.view.viewmodel.IMCViewModel
import com.example.mvvm.view.listener.OnIMCListener

class IMCActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityImcactivityBinding

    private lateinit var imcViewModel: IMCViewModel
    private val adapter = IMCAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imcViewModel = ViewModelProvider(this).get(IMCViewModel::class.java)

        //layout recycler
        binding.recyclerImc.layoutManager = LinearLayoutManager(applicationContext)

        //adapter recycler
        binding.recyclerImc.adapter = adapter

        val listener = object: OnIMCListener {
            override fun onClick(id: Int) {
                Toast.makeText(applicationContext, "Clicando no id: $id", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                imcViewModel.deleteData(id)
                imcViewModel.selectAllData()

            }
        }

        adapter.getListener(listener)

        imcViewModel.selectAllData()

        binding.btnImc.setOnClickListener(this)

        setObserve()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_imc) {
            var altura = binding.editTextAltura.text.toString()
            var peso = binding.editTextPeso.text.toString()
            var imc = imcViewModel.calculateIMC2(altura, peso)

            var imcModel = IMCModel(0, peso, altura, imc)

            imcViewModel.insertData(imcModel)
            imcViewModel.selectAllData()

            if ((altura.isEmpty() || altura == null) || peso.isEmpty() || peso == null) {
                Toast.makeText(applicationContext, "Preencher todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                imcViewModel.calculateIMC(altura, peso)
            }
        }
    }

    private fun setObserve() {
        imcViewModel.listImc.observe(this, Observer {
            adapter.updateImc(it)
        })
        imcViewModel.imcReturn().observe(this, Observer {
            binding.textViewImc.text = it
        })
    }
}