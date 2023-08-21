package com.example.viewpager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.viewpager.model.Favorite


@Database(entities = [Favorite::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}