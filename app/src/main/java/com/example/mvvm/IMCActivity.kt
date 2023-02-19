package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityImcactivityBinding

class IMCActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityImcactivityBinding

    private lateinit var imcViewModel: IMCViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imcViewModel = ViewModelProvider(this).get(IMCViewModel::class.java)

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
            val string = ""
        })
    }
}