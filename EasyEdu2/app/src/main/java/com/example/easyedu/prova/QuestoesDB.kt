package com.example.easyedu.prova

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Questao::class], version=1)
abstract class QuestoesDB : RoomDatabase() {
    abstract fun questoesDAO(): QuestoesDAO
    companion object {
        private var INSTANCE: QuestoesDB? = null
        fun getDatabase(context: Context): QuestoesDB {
            if (INSTANCE == null) {
                synchronized(QuestoesDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        QuestoesDB::class.java, "questoesDB.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}