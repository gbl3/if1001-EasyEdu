package com.example.easyedu.prova

import androidx.room.*

@Dao
interface ProvasDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirProva(vararg prova: Prova)

    @Update
    fun atualizarProva(vararg prova: Prova)

    @Delete
    fun removerProva(vararg prova: Prova)

    @Query("SELECT * FROM provas")
    fun todasProvas() : Array<Prova>
}