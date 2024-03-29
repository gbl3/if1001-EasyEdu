package com.example.easyedu.posts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var msg: String,
    var turmaId: Int
) {
    override fun toString(): String {
        return msg
    }
}