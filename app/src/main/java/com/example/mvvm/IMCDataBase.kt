package com.example.mvvm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class IMCDataBase(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION ) {

    companion object {
        const val NAME = "imcdb"
        const val VERSION = 1
    }
    
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table imc (id integer primary key autoincrement, peso text, altura text, imc text);")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}