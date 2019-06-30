package com.example.easyedu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version=1)
abstract class UsuariosDB : RoomDatabase() {
    abstract fun usuariosDAO(): UsuariosDAO
    companion object {
        private var INSTANCE: UsuariosDB? = null
        fun getDatabase(context: Context): UsuariosDB {
            if (INSTANCE == null) {
                synchronized(UsuariosDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        UsuariosDB::class.java, "usuariosDB.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}