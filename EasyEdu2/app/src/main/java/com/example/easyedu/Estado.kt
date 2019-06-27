package br.ufpe.cin.android.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estados")
data class Estado(
    @PrimaryKey (autoGenerate = true) var uf: Long,
    var nome: String
//    var id :
) {
    override fun toString(): String {
        return nome
    }
}