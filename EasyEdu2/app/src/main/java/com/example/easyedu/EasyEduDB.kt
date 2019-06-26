package com.example.easyedu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Turma::class), version=1)
abstract class EasyEduDB : RoomDatabase() {
    abstract fun easyEduDAO(): EasyEduDAO
    companion object {
        private var INSTANCE: EasyEduDB? = null
        fun getDatabase(context: Context): EasyEduDB {
            if (INSTANCE == null) {
                synchronized(EasyEduDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        EasyEduDB::class.java, "easyEdu.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}