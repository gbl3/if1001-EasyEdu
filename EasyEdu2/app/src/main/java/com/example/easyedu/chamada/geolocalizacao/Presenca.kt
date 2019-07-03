package com.example.easyedu.chamada.geolocalizacao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "presenca")
data class Presenca(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var idAluno: Int,
    var presenca: String

) {
    override fun toString(): String {
        return "O Aluno de ID: " + idAluno.toString() + " está presente."
    }
}