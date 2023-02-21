package com.example.mvvm.repository

import android.content.ContentValues
import android.content.Context
import com.example.mvvm.constants.IMCBaseConstants
import com.example.mvvm.model.IMCModel

class IMCRepository private constructor(context: Context){

    private val imcDataBase = IMCDataBase(context)

    companion object {
        private lateinit var repository: IMCRepository

        fun getInstance(context: Context): IMCRepository {
            if(!Companion::repository.isInitialized) {
                repository = IMCRepository(context)
            }
            return repository
        }
    }

    fun insertData(imcModel: IMCModel): Boolean {
        return try {
            val db = imcDataBase.writableDatabase

            val values = ContentValues()
            values.put(IMCBaseConstants.Imc.COLUMNS.PESO, imcModel.peso)
            values.put(IMCBaseConstants.Imc.COLUMNS.ALTURA, imcModel.altura)
            values.put(IMCBaseConstants.Imc.COLUMNS.IMC, imcModel.imc)

            db.insert(IMCBaseConstants.Imc.TABLE_NAME, null, values)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun updateData(imcModel: IMCModel): Boolean {
        return try {
            //abrindo o BD
            val db = imcDataBase.writableDatabase

            //criando o values e inserindo valores nele baseado nas colunas/imcModel
            val values = ContentValues()

            values.put(IMCBaseConstants.Imc.COLUMNS.PESO, imcModel.peso)
            values.put(IMCBaseConstants.Imc.COLUMNS.ALTURA, imcModel.altura)
            values.put(IMCBaseConstants.Imc.COLUMNS.IMC, imcModel.imc)

            //selection passando o ID
            val selection = IMCBaseConstants.Imc.COLUMNS.ID + " = ?"
            val args = arrayOf((imcModel.id).toString())

            db.update(IMCBaseConstants.Imc.TABLE_NAME,values, selection, args)
            true

        } catch (e: Exception) {
            false
        }
    }

    fun deleteData(imcId: Int): Boolean {
        return try {
            val db = imcDataBase.writableDatabase

            val selection = IMCBaseConstants.Imc.COLUMNS.ID + " = ?"
            val args = arrayOf((imcId).toString())

            db.delete(IMCBaseConstants.Imc.TABLE_NAME, selection, args)
            true

        } catch (e: Exception) {
            false
        }
    }

    fun selectAllData(): List<IMCModel> {
        val imcList = mutableListOf<IMCModel>()

        try {
            val db = imcDataBase.readableDatabase

            val columns = arrayOf(
                IMCBaseConstants.Imc.COLUMNS.ID,
                IMCBaseConstants.Imc.COLUMNS.PESO,
                IMCBaseConstants.Imc.COLUMNS.ALTURA,
                IMCBaseConstants.Imc.COLUMNS.IMC
            )

            //método query retorna um cursor (aponta para o começo da tabela)
            val cursor = db.query(
                IMCBaseConstants.Imc.TABLE_NAME, columns, null, null,
                null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    //pegando o id atraves do cursor, indo ate a coluna ID
                    val id = cursor.getInt(cursor.getColumnIndex(IMCBaseConstants.Imc.COLUMNS.ID))
                    val peso = cursor.getString(cursor.getColumnIndex(IMCBaseConstants.Imc.COLUMNS.PESO))
                    val altura = cursor.getString(cursor.getColumnIndex(IMCBaseConstants.Imc.COLUMNS.ALTURA))
                    val imc = cursor.getString(cursor.getColumnIndex(IMCBaseConstants.Imc.COLUMNS.IMC))

                    val imcModel = IMCModel(id, peso, altura, imc)
                    imcList.add(imcModel)
                }

            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return imcList

        }
        return imcList
    }
}