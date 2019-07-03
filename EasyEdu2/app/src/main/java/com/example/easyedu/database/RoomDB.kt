package com.example.easyedu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easyedu.Usuario
import com.example.easyedu.chamada.Professor
import com.example.easyedu.chamada.geolocalizacao.Presenca
import com.example.easyedu.posts.Post
import com.example.easyedu.prova.Questao
import com.example.easyedu.turmas.Turma
import com.example.easyedu.users.UsuarioAtual

@Database(entities = [Usuario::class, UsuarioAtual::class, Post::class, Questao::class, Turma::class, Presenca::class, Professor::class], version=1)
abstract class RoomDB : RoomDatabase() {
    abstract fun roomDAO(): RoomDAO
    companion object {
        private var INSTANCE: RoomDB? = null
        fun getDatabase(context: Context): RoomDB {
            if (INSTANCE == null) {
                synchronized(RoomDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        RoomDB::class.java, "roomDB.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}