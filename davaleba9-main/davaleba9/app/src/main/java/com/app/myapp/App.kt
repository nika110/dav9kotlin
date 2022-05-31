package com.zura.mysuperapp

import android.app.Application
import androidx.room.Room
import com.zura.mysuperapp.database.AppDatabase

class App: Application() {

    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        app = this

        db = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "DATABASE1")
            .allowMainThreadQueries().build()
    }

    companion object{
        lateinit var app: App
            private set

        var externalDataReceived: Boolean = false
        var externalDataLoaded: Boolean = false

    }

}