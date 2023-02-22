package com.example.mvvm.constants

class IMCBaseConstants private constructor() {
    object Imc {
        const val TABLE_NAME = "Imc"
        const val ID = "imcid"

        object COLUMNS {
            const val ID = "id"
            const val PESO = "peso"
            const val ALTURA = "altura"
            const val IMC = "imc"
        }
    }
}