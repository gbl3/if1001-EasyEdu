package com.example.easyedu.database

import androidx.room.*
import com.example.easyedu.Usuario
import com.example.easyedu.chamada.Professor
import com.example.easyedu.chamada.geolocalizacao.Presenca

@Dao
interface RoomDAO {
    // PresencaDAO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirPresenca(vararg presenca: Presenca)

    @Query("SELECT * FROM presenca")
    fun todasPresencas() : Array<Presenca>

    // ProfessorDAO
    @Query("SELECT * FROM professor")
    fun todosProf() : Array<Professor>

    @Update
    fun atualizarProf(vararg professor: Professor)


    //UsuarioDAO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirUsuarios(vararg usuario: Usuario)

    @Update
    fun atualizarUsuarios(vararg usuario: Usuario)

    @Delete
    fun removerUsuarios(vararg usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    fun todosUsuarios() : Array<Usuario>

    @Query("SELECT COUNT(*) FROM usuarios")
    fun todoscOUNT() : Int

    @Query("SELECT * FROM usuarios WHERE email LIKE :q")
    fun buscaUsuarioPeloEmail(q : String) : List<Usuario>

    @Query("SELECT * FROM usuarios WHERE email LIKE :q")
    fun validaLogin(q : String) : Usuario

    @Query("SELECT senha FROM usuarios WHERE email LIKE :q")
    fun validaSenha(q : String) : String

    @Query("SELECT * FROM usuarios WHERE id LIKE :q")
    fun buscaUsuarioPeloId(q : String) : List<Usuario>

    @Query("SELECT perfil FROM usuarios WHERE id LIKE :q")
    fun buscaPerfilPorId(q : Int) : Int
}