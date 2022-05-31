package com.IoTProject.canseeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.net.Socket

class VideolistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.videolist_layout)

        Log.d("SocketStatus",SocketClass.getSocketStatus().toString())
    }
}