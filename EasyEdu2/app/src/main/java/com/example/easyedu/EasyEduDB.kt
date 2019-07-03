package com.example.easyedu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easyedu.posts.Post
import com.example.easyedu.posts.PostsDAO
import com.example.easyedu.prova.Prova
import com.example.easyedu.prova.ProvasDAO
import com.example.easyedu.prova.questoes.Questao
import com.example.easyedu.prova.questoes.QuestoesDAO
import com.example.easyedu.turmas.Turma
import com.example.easyedu.turmas.TurmasDAO

@Database(entities = [
    Turma::class,
    Prova::class,
    Questao::class,
    Post::class
], version = 2)

abstract class EasyEduDB : RoomDatabase() {
    abstract fun turmasDAO(): TurmasDAO
    abstract fun provasDao(): ProvasDAO
    abstract fun questoesDAO(): QuestoesDAO
    abstract fun postsDAO(): PostsDAO


    companion object {
        private var INSTANCE: EasyEduDB? = null
        fun getDatabase(context: Context): EasyEduDB {
            if (INSTANCE == null) {
                synchronized(EasyEduDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EasyEduDB::class.java, "easyEdu.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}