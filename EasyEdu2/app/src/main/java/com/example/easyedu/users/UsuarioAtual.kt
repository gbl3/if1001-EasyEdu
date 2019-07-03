package com.example.easyedu.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarioatual")
data class UsuarioAtual(
    @PrimaryKey var id: Int,
    var email: String,
    var perfil: Int

) {
    override fun toString(): String {
        return email
    }
}