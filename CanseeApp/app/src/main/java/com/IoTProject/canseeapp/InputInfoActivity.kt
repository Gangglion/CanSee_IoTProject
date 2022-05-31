package com.IoTProject.canseeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import io.socket.client.IO
import io.socket.client.Socket

class InputInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inputinfo_layout)

        var confirm_btn : Button = findViewById(R.id.confirm_button)
        var addressinput : EditText = findViewById(R.id.inputaddress)
        var portinput : EditText = findViewById(R.id.inputport)

        // 주소와 포트 입력 후 확인 눌렀을때 연결시키고 성공이라면 서버에서 메시지 받고
        // 메시지 식별하여 다음 액티비티로 넘어감
        confirm_btn.setOnClickListener {
            var inputAddress : String = addressinput.text.toString()
            var inputPort : String = portinput.text.toString()
            var socketTh = SocketThread(inputAddress,inputPort)
            var t = Thread(socketTh)
            t.start()
            Thread.sleep(500)
            if(SocketClass.getSocketStatus())
            {
                var intent = Intent(this,VideolistActivity::class.java)
                startActivity(intent)
            }
        }

    }
    class SocketThread(address:String,port:String) : Runnable{
        lateinit var socket: Socket
        var address : String
        var port : String

        init{
            this.address = address
            this.port = port
        }
        override fun run() {
            try{
                socket = SocketClass.get(address,port)
                socket.connect()
            }catch(e:Exception){
                Log.e("Socketerr","Error")
            }
        }
    }
}