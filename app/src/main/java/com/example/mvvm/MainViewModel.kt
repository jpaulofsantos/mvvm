package com.example.mvvm

import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //MutableLiveData será observada e conseguimos responder aos eventos
    private var textWelcome = MutableLiveData<String>()
    private var login = MutableLiveData<Boolean>()
    private var personRepository: PersonRepository = PersonRepository()
    private var checkBox = MutableLiveData<Boolean>()
    private var strBtn = MutableLiveData<String>()

    private var textBtnNew = MutableLiveData<String>().apply {
        value = "Live Data"
    }

    var textBtn: LiveData<String> = textBtnNew


    private var textWelcome2 = MutableLiveData<String>().apply {
        value = "Login Screen 2"
    }

    var newTextWelcome: LiveData<String> = textWelcome2

    //setando um valor teste para a variável textWelcome
    init {
        textWelcome.value = "Login Screen!"

    }

    fun welcome():LiveData<String> {
        return textWelcome
    }

    //observer
    fun login(): LiveData<Boolean> {
        return login
    }

    fun doLogin(email: String, senha: String) {
        login.value = personRepository.login(email, senha)
    }

    //observer
    fun checkBox(): LiveData<Boolean> {
        return checkBox
    }

    fun analyseCheckBox(checked: Boolean) {
        checkBox.value = checked
    }

    //observer
    fun btn(): LiveData<String> {
        return strBtn
    }

    fun changeBtn(str: String) {
        strBtn.value = str
    }
}