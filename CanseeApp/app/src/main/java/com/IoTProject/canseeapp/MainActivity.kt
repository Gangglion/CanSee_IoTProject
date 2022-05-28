package com.IoTProject.canseeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.socket.client.IO

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mSocket = RunSocketThread()
        mSocket.start()
    }

    class RunSocketThread : Thread(){
        lateinit var msocket:io.socket.client.Socket
        override fun run() {
            super.run()
            try{
                msocket = IO.socket("http://192.168.0.100:3000")
            }catch(e:Exception){
                Log.e("error",e.printStackTrace().toString())
            }
            msocket.connect()
        }
    }
}