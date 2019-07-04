package com.example.easyedu.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easyedu.UsuarioPertenceTurma

@Dao
interface UsuarioPertenceTurmaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirNaTurma(vararg usuarioPertenceTurma: UsuarioPertenceTurma)

    @Query("SELECT * FROM turmasUsuario WHERE idUsuario LIKE :q")
    fun buscaTurmaPorUserId(q : Int) : List<UsuarioPertenceTurma>
}