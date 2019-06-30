package com.example.easyedu.prova.questoes

import androidx.room.*

@Dao
interface QuestoesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirQuestao(vararg questao: Questao)

    @Update
    fun atualizarQuestao(vararg questao: Questao)

    @Delete
    fun removerQuestao(vararg questao: Questao)

    @Query("SELECT * FROM questoes")
    fun todasQuestoes() : Array<Questao>
}