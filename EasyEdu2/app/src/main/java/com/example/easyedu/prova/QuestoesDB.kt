package com.example.easyedu.prova

import com.example.easyedu.Usuario
import com.example.easyedu.UsuariosDAO
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Questao::class,Teste::class], version=1)
abstract class TestesGeral : RoomDatabase() {
    abstract fun testesDAO(): TestesDAO
    companion object {
        private var INSTANCE: TestesGeral? = null
        fun getDatabase(context: Context): TestesGeral {
            if (INSTANCE == null) {
                synchronized(TestesGeral::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        TestesGeral::class.java, "testesGeral.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}