package com.example.easyedu.chamada.geolocalizacao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PresencaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirPresenca(vararg presenca: Presenca)

    @Query("SELECT * FROM presenca")
    fun todasPresencas() : Array<Presenca>
}