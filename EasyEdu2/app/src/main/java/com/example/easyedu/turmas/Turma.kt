package com.example.easyedu.turmas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turmas")
data class Turma(
    @PrimaryKey var id: String,
    var nome: String
) {
    override fun toString(): String {
        return nome
    }
}