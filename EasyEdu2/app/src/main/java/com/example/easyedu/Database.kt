package com.example.easyedu


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): PostDAO
}