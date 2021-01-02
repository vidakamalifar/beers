package com.fob.beers

import android.app.Application
import androidx.room.Room
import com.fob.beers.databease.AppDatabase

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDb()
    }

    fun initDb(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "beer-db"
        ).build()
    }
}