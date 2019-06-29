package com.example.easyedu.prova

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestoesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirQuestao(vararg questao: Questao)


}