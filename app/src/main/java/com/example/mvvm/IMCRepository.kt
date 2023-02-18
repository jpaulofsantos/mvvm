package com.example.mvvm

import android.content.ContentValues
import android.content.Context

class IMCRepository private constructor(context: Context){

    private val imcDataBase = IMCDataBase(context)

    companion object {
        private lateinit var repository: IMCRepository

        fun getInstance(context: Context): IMCRepository {
            if(!::repository.isInitialized) {
                repository = IMCRepository(context)
            }
            return repository
        }
    }

    fun insertData(imcModel: IMCModel): Boolean {
        return try {
            val db = imcDataBase.writableDatabase
            val values = ContentValues()
            values.put("peso", imcModel.peso)
            values.put("altura", imcModel.altura)
            values.put("imc", imcModel.imc)

            db.insert("imc", null, values)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }
}