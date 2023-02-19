package com.example.mvvm

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IMCViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = IMCRepository.getInstance(application)

    private val imc = MutableLiveData<String>()

    fun imcReturn(): LiveData<String> {
        return imc
    }

    private val listaImc = MutableLiveData<List<IMCModel>>()

    val listImc: LiveData<List<IMCModel>> = listaImc

    fun calculateIMC(altura:String, peso:String) {
        var pesoFloat = peso.toFloat()
        var alturaFloat = altura.toFloat() / 100
        var calculoIMC = pesoFloat / (alturaFloat * alturaFloat)
        imc.value = calculoIMC.toString()
    }

    fun calculateIMC2(altura:String, peso:String): String {
        var pesoFloat = peso.toFloat()
        var alturaFloat = altura.toFloat() / 100
        var calculoIMC = pesoFloat / (alturaFloat * alturaFloat)
        return calculoIMC.toString()
    }

    fun insertData(imcModel: IMCModel) {
        repository.insertData(imcModel)
    }

    fun updateData(imcModel: IMCModel) {
        repository.updateData(imcModel)
    }

    fun deleteData(imcId: Int) {
        repository.deleteData(imcId)
    }

    fun selectAllData() {
        listaImc.value = repository.selectAllData()
    }
}