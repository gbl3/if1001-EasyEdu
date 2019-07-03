package com.example.easyedu.prova.questoes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questoes")
data class Questao(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var enunciado: String,
    var tipo: Int,
    var alternativas: Array<String>?,
    var idProva: Int
) {
    override fun toString(): String {
        return enunciado
    }
}