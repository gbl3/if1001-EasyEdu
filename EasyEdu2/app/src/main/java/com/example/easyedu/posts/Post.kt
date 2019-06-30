package com.example.easyedu.posts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey var id: String,
    var msg: String
) {
    override fun toString(): String {
        return msg
    }
}