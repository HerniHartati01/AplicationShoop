package com.example.viewpager

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var database: Database
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            Database::class.java, "database"
        ).fallbackToDestructiveMigration().build()
    }
}