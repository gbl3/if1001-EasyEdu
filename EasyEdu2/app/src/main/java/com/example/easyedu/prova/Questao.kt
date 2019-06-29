package com.example.easyedu.prova

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "questao")
data class Questao(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var enunciado: String,
    var tipo: Int
) {
    override fun toString(): String {
        return enunciado
    }
}

@Entity(tableName = "teste",
    foreignKeys = arrayOf(ForeignKey(
        entity = Questao::class, parentColumns = arrayOf("id"),
        childColumns = arrayOf("t2"),
        onDelete = ForeignKey.CASCADE)))

data class Teste(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var t1: String,
    var t2: Int
){
    override fun toString(): String {
        return t1
    }
}