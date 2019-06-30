package com.example.easyedu.turmas

import androidx.room.*

@Dao
interface TurmasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirTurmas(vararg turma: Turma)

    @Update
    fun atualizarTurmas(vararg turma: Turma)

    @Delete
    fun removerTurmas(vararg turma: Turma)

    @Query("SELECT * FROM turmas")
    fun todasTurmas() : Array<Turma>

    @Query("SELECT * FROM turmas WHERE nome LIKE :q")
    fun buscaTurmaPeloNome(q : String) : List<Turma>

    @Query("SELECT * FROM turmas WHERE id LIKE :q")
    fun buscaTurmaPeloId(q : String) : List<Turma>
}