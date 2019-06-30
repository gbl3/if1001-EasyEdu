package com.example.easyedu.prova

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.easyedu.prova.questoes.Questao

@Entity(
    foreignKeys = [ForeignKey(
        entity = Questao::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("questaoId")
    )],
    tableName = "provas"
)
data class Prova(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var titulo: String,
    val questaoId: Int
) {
    override fun toString(): String {
        return titulo
    }
}