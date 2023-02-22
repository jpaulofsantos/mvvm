package com.example.mvvm.view.viewholder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.constants.IMCBaseConstants
import com.example.mvvm.databinding.ActivityImcactivityBinding
import com.example.mvvm.model.IMCModel
import com.example.mvvm.view.adapter.IMCAdapter
import com.example.mvvm.view.viewmodel.IMCViewModel
import com.example.mvvm.view.listener.OnIMCListener

class IMCActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityImcactivityBinding

    private lateinit var imcViewModel: IMCViewModel
    private val adapter = IMCAdapter()
    private var imcId = 0

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
                val intent = Intent(applicationContext, IMCActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(IMCBaseConstants.Imc.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                imcViewModel.deleteData(id)
                imcViewModel.selectAllData()

            }
        }

        adapter.getListener(listener)

        binding.btnImc.setOnClickListener(this)

        setObserve()

        loadData()
    }

    override fun onResume() {
        super.onResume()
        imcViewModel.selectAllData()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_imc) {
            var altura = binding.editTextAltura.text.toString()
            var peso = binding.editTextPeso.text.toString()
            var imc = imcViewModel.calculateIMC2(altura, peso)

            var imcModel = IMCModel(imcId, peso, altura, imc)

            if (imcModel.id == 0) {
                imcViewModel.insertData(imcModel)
            } else {
                imcViewModel.updateData(imcModel)
            }
            imcViewModel.selectAllData()

            if ((altura.isEmpty() || altura == null) || peso.isEmpty() || peso == null) {
                Toast.makeText(applicationContext, "Preencher todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                imcViewModel.calculateIMC(altura, peso)
            }
        }
    }

    //traz os dados referente ao registro selecionado
    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            imcId = bundle.getInt(IMCBaseConstants.Imc.ID)
            imcViewModel.getIMC(imcId)
        }
    }

    private fun setObserve() {
        imcViewModel.listImc.observe(this, Observer {
            adapter.updateImc(it)
        })
        imcViewModel.imcReturn().observe(this, Observer {
            binding.textViewImc.text = it
        })

        //observe na variável imcNew que é alterada pelo getImc. que é chamado pelo método loadData()
        imcViewModel.imcNew.observe(this, Observer {
            binding.btnImc.text = "Editar"
            binding.editTextPeso.setText(it.peso)
            binding.editTextAltura.setText(it.altura)
        })
    }
}