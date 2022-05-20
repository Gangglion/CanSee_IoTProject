package com.example.sockettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.net.Socket

class MainActivity : AppCompatActivity() {
//    lateinit var socket:Socket
//    lateinit var button: Button
//    lateinit var editText: EditText
//    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ClientThread().start()
//        try{
//            socket = Socket("127.0.0.1",3000);
//        }catch(e:Exception){
//            e.printStackTrace();
//        }
    }
    class ClientThread:Thread(){
        override fun run(){
            var socket = Socket("192.168.0.19",3000)

        }
    }
}