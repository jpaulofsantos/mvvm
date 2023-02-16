package com.example.mvvm

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IMCViewModel: ViewModel() {

    private val imc = MutableLiveData<String>()

    fun imcReturn(): LiveData<String> {
        return imc
    }

    fun calculateIMC(altura:String, peso:String) {
        var pesoFloat = peso.toFloat()
        var alturaFloat = altura.toFloat() / 100
        var calculoIMC = pesoFloat / (alturaFloat * alturaFloat)
        imc.value = calculoIMC.toString()
    }
}