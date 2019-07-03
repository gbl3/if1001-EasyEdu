package com.example.easyedu.prova

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "provas"
)
data class Prova(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var titulo: String,
    var turmaId: Int
) {
    override fun toString(): String {
        return titulo
    }
}