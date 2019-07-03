package com.example.easyedu.chamada

import androidx.room.*

@Dao
interface ProfessorDAO {
    @Query("SELECT * FROM professor")
    fun todosProf() : Array<Professor>

    @Update
    fun atualizarProf(vararg professor: Professor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirProfessor(vararg professor: Professor)
}