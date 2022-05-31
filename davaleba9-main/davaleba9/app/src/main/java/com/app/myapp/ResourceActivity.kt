package com.zura.mysuperapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.zura.mysuperapp.api.dto.ReqResData
import com.zura.mysuperapp.api.dto.Resource
import com.zura.mysuperapp.fragments.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResourceActivity : AppCompatActivity() {
    private lateinit var id: TextView
    private lateinit var name: TextView
    private lateinit var year: TextView
    private lateinit var color: TextView
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)


        id = findViewById(R.id.activity_id)
        name = findViewById(R.id.activity_name)
        year = findViewById(R.id.activity_year)
        color = findViewById(R.id.activity_color)

        userId = intent.extras?.getInt("resource_id", -1) !!

        if(userId != -1){
            RestClient.reqResApi.getResource(userId).enqueue(object: Callback<ReqResData<Resource>>{
                override fun onResponse(
                    call: Call<ReqResData<Resource>>,
                    response: Response<ReqResData<Resource>>
                ) {
                    id.text = response.body()?.data?.id.toString()
                    name.text = response.body()?.data?.name.toString()
                    year.text = response.body()?.data?.year.toString()
                    color.text = response.body()?.data?.color.toString()
                    color.setBackgroundColor(Color.parseColor(color.text.toString()))
                }

                override fun onFailure(call: Call<ReqResData<Resource>>, t: Throwable) {
                    Log.d("error", t.message.toString())
                }

            })
        }
    }
}