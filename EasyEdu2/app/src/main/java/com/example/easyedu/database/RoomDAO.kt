package com.example.easyedu.database

import androidx.room.*
import com.example.easyedu.Usuario
import com.example.easyedu.chamada.Professor
import com.example.easyedu.chamada.geolocalizacao.Presenca
import com.example.easyedu.posts.Post
import com.example.easyedu.prova.questoes.Questao
import com.example.easyedu.turmas.Turma
import com.example.easyedu.users.UsuarioAtual

@Dao
interface RoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirPosts(vararg post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirPresenca(vararg presenca: Presenca)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirProfessor(vararg professor: Professor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirQuestao(vararg questao: Questao)

    @Update
    fun atualizarPosts(vararg post: Post)

    @Delete
    fun removerPosts(vararg post: Post)

    @Query("SELECT * FROM posts")
    fun todosPosts() : Array<Post>

    @Query("SELECT * FROM professor")
    fun todosProf() : Array<Professor>

    @Query("SELECT * FROM posts WHERE msg LIKE :q")
    fun buscaPostPelaMsg(q : String) : List<Post>

    @Query("SELECT * FROM posts WHERE id LIKE :q")
    fun buscaPostPeloId(q : String) : List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirTurmas(vararg turma: Turma)

    @Update
    fun atualizarTurmas(vararg turma: Turma)

    @Update
    fun atualizarProf(vararg professor: Professor)

    @Delete
    fun removerTurmas(vararg turma: Turma)

    @Query("SELECT * FROM turmas")
    fun todasTurmas() : Array<Turma>

    @Query("SELECT * FROM turmas WHERE nome LIKE :q")
    fun buscaTurmaPeloNome(q : String) : List<Turma>

    @Query("SELECT * FROM turmas WHERE id LIKE :q")
    fun buscaTurmaPeloId(q : String) : List<Turma>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirAtual(vararg usuarioAtual: UsuarioAtual)

    @Delete
    fun removerAtual(vararg usuarioAtual: UsuarioAtual)

    @Query("SELECT * FROM usuarioatual")
    fun saberPerfilLogado() : List<UsuarioAtual>

    @Query("SELECT COUNT(*) FROM usuarioatual")
    fun saberSeExiste() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirUsuarios(vararg usuario: Usuario)
    //
    @Update
    fun atualizarUsuarios(vararg usuario: Usuario)

    @Delete
    fun removerUsuarios(vararg usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    fun todosUsuarios() : Array<Usuario>

    @Query("SELECT * FROM presenca")
    fun todasPresencas() : Array<Presenca>

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