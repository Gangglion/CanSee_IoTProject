package com.example.sockettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.socket.client.IO
import io.socket.client.IO.socket
import java.net.Socket

class MainActivity : AppCompatActivity() {
//    lateinit var socket:Socket
//    lateinit var button: Button
//    lateinit var editText: EditText
//    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var mSocket = SocketApplication()
        var mSocket = SocketApplicationLib()
        mSocket.start()
    }

    // 방법 1 : socket.io 라이브러리 사용하지 않은 기본적인 방법
//    class SocketApplication: Thread() {
//        lateinit var msocket:Socket
//        override fun run() {
//            super.run()
//            try{
//                msocket=Socket("192.168.0.101",3000) // Socket 생성자에 호스트와 포트 입력해주면 바로 해당 호스트로 연결됨
//            }catch (e:Exception){
//                Log.e("error",e.printStackTrace().toString())
//            }
//        }
//    }

    // 방법 2 : socket.io 라이브러리 사용하는 방법
    class SocketApplicationLib:Thread(){
        lateinit var msocket:io.socket.client.Socket
        override fun run() {
            super.run()
            try{
                msocket=IO.socket("http://192.168.0.101:3000")
            }catch(e:Exception) {
                Log.e("error", e.printStackTrace().toString())
            }
            msocket.connect()
        }
    }
}