package com.example.easyedu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turmasUsuario")
data class UsuarioPertenceTurma(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var idUsuario: Int,
    var idTurma: Int
    )
{
    override fun toString(): String {
        return id.toString()
    }
}