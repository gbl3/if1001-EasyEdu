package com.example.easyedu.turmas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "turmas"
)
data class Turma(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var nome: String
) {
    override fun toString(): String {
        return nome
    }
}