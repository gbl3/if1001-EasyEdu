package com.example.easyedu.users

import androidx.room.*

@Dao
interface UsuarioAtualDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirAtual(vararg usuarioAtual: UsuarioAtual)

    @Delete
    fun removerAtual(vararg usuarioAtual: UsuarioAtual)

    @Query("SELECT * FROM usuarioatual")
    fun saberPerfilLogado() : List<UsuarioAtual>
}