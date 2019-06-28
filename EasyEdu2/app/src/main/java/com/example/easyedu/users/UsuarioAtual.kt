package com.example.easyedu.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_atual")
data class UsuarioAtual(
    @PrimaryKey var id: Int

) {
    fun toInt(): Int {
        return id
    }
}