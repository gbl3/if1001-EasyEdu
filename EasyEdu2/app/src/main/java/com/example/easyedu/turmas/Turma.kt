package com.example.easyedu.turmas

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.easyedu.prova.Prova

@Entity(
    foreignKeys = [ForeignKey(
        entity = Prova::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("provaId")
    )],
    tableName = "turmas"
)
data class Turma(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    var nome: String,
    val provaId: Int?

) {
    override fun toString(): String {
        return nome
    }
}