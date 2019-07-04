package com.example.easyedu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easyedu.chamada.Professor
import com.example.easyedu.chamada.ProfessorDAO
import com.example.easyedu.chamada.geolocalizacao.Presenca
import com.example.easyedu.chamada.geolocalizacao.PresencaDAO
import com.example.easyedu.posts.Post
import com.example.easyedu.posts.PostsDAO
import com.example.easyedu.prova.Prova
import com.example.easyedu.prova.ProvasDAO
import com.example.easyedu.prova.questoes.Questao
import com.example.easyedu.prova.questoes.QuestoesDAO
import com.example.easyedu.turmas.Turma
import com.example.easyedu.turmas.TurmasDAO
import com.example.easyedu.users.UsuarioAtual
import com.example.easyedu.users.UsuarioAtualDAO
import com.example.easyedu.users.UsuarioDAO
import com.example.easyedu.users.UsuarioPertenceTurmaDAO

@Database(entities = [
    Turma::class,
    Prova::class,
    Questao::class,
    Post::class,
    Usuario::class,
    UsuarioAtual::class,
    Presenca::class,
    Professor::class,
    UsuarioPertenceTurma::class
], version = 1)

abstract class EasyEduDB : RoomDatabase() {
    abstract fun turmasDAO(): TurmasDAO
    abstract fun provasDao(): ProvasDAO
    abstract fun questoesDAO(): QuestoesDAO
    abstract fun postsDAO(): PostsDAO
    abstract fun usuarioAtualDAO(): UsuarioAtualDAO
    abstract fun usuarioPertenceTurmaDAO(): UsuarioPertenceTurmaDAO
    abstract fun usuarioDAO(): UsuarioDAO
    abstract fun professorDAO(): ProfessorDAO
    abstract fun presencaDAO(): PresencaDAO



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