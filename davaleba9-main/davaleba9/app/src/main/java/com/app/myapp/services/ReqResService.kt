package com.zura.mysuperapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import com.zura.mysuperapp.App
import com.zura.mysuperapp.api.dto.ReqResData
import com.zura.mysuperapp.api.dto.Resource
import com.zura.mysuperapp.database.daos.ResourceDao
import com.zura.mysuperapp.fragments.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReqResService: Service(){
    private lateinit var handler: Handler
    private lateinit var  handleThread: HandlerThread
    private lateinit var resourceDao: ResourceDao


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        handleThread = HandlerThread("ReqRes")
        handleThread.start()
        handler = Handler(handleThread.looper)
        resourceDao = App.app.db.getResourcesDao()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        handler.post {
            RestClient.reqResApi.getResources().enqueue(object :
                Callback<ReqResData<List<Resource>>> {
                override fun onResponse(
                    call: Call<ReqResData<List<Resource>>>,
                    response: Response<ReqResData<List<Resource>>>
                ) {
                    resourceDao.clear()
                    response.body()?.data?.let {
                        for(resource in response.body()?.data!!){
                            resourceDao.insert(resource)                     }
                    }

                    App.externalDataReceived = true
                }

                override fun onFailure(call: Call<ReqResData<List<Resource>>>, t: Throwable) {
                    Log.d("error", call.isExecuted.toString())
                    Log.d("error", t.message.toString())
                }
            })
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}