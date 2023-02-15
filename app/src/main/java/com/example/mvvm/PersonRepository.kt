package com.example.mvvm

class PersonRepository {

    fun login(email: String, senha: String): Boolean {
        return !((email.isEmpty() || email == null) || (senha.isEmpty() || senha == null))
    }
}