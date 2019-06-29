package com.example.easyedu.prova

import android.database.Cursor
import com.example.easyedu.users.UsuarioAtual
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirTeste(vararg teste: Teste)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirQuestao(vararg questao: Questao)

    @Query("SELECT COUNT(*) FROM teste t INNER JOIN questao q ON q.id = t.t2")
    fun tudo(): Int
}