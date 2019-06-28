package com.example.easyedu.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface UsuarioAtualDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirAtual(vararg usuarioAtual: UsuarioAtual)
}