package com.zura.mysuperapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import com.zura.mysuperapp.App
import com.zura.mysuperapp.database.daos.ResourceDao

class DatabaseService: Service() {
    private lateinit var handler: Handler
    private lateinit var  handleThread: HandlerThread
    private lateinit var resourceDao: ResourceDao


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        handleThread = HandlerThread("Database")
        handleThread.start()
        handler = Handler(handleThread.looper)
        resourceDao = App.app.db.getResourcesDao()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post {
            Log.d("Database", "starting")

            while (!App.externalDataReceived){
                Thread.sleep(1000)
            }

            resourceDao.deleteExceptTopThree()
            App.externalDataLoaded = true
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}