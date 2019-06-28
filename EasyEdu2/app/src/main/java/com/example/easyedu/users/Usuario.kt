package com.example.easyedu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var email: String,
    var senha: String,
    var perfil: Int
    ) {
    override fun toString(): String {
        return email
    }
}