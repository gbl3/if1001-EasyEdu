package com.example.easyedu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Post::class), version=1)
abstract class PostsDB : RoomDatabase() {
    abstract fun postsDAO(): PostsDAO
    companion object {
        private var INSTANCE: PostsDB? = null
        fun getDatabase(context: Context): PostsDB {
            if (INSTANCE == null) {
                synchronized(PostsDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        PostsDB::class.java, "PostsDB.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}