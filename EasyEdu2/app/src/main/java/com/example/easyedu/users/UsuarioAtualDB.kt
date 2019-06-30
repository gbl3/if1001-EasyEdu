package com.example.easyedu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easyedu.users.UsuarioAtual
import com.example.easyedu.users.UsuarioAtualDAO

@Database(entities = [UsuarioAtual::class], version=1)
abstract class UsuarioAtualDB : RoomDatabase() {
    abstract fun usuarioAtualDAO(): UsuarioAtualDAO
    companion object {
        private var INSTANCE: UsuarioAtualDB? = null
        fun getDatabase(context: Context): UsuarioAtualDB {
            if (INSTANCE == null) {
                synchronized(UsuarioAtualDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        UsuarioAtualDB::class.java, "usuarioAtualDB.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}