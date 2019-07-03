package com.example.easyedu.chamada

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "professor")
data class Professor(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var userID: Int,
    var latitude: String,
    var longitude: String
) {
    override fun toString(): String {
        return latitude
    }
}