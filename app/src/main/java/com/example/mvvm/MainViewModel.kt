package com.example.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //MutableLiveData será observada e conseguimos responder aos eventos
    private var textWelcome = MutableLiveData<String>()

    //setando um valor teste para a variável textWelcome
    init {
        textWelcome.value = "Olá"
    }

    fun welcome():LiveData<String> {
        return textWelcome

    }

}