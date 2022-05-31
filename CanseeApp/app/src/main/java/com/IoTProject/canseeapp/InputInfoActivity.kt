package com.IoTProject.canseeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.socket.client.IO

class InputInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inputinfo_layout)

        var confirm_btn : Button = findViewById(R.id.confirm_button)
        var addressinput : EditText = findViewById(R.id.inputaddress)
        var portinput : EditText = findViewById(R.id.inputport)

        // 주소와 포트 입력 후 확인 눌렀을때 연결되는지 여부
        confirm_btn.setOnClickListener {
            var inputAddress : String = addressinput.text.toString()
            var inputPort : String = portinput.text.toString()
            var mSocket = RunSocketThread(inputAddress, inputPort)
            mSocket.start()
        }
    }

    class RunSocketThread(address: String,port : String) : Thread(){
        lateinit var msocket:io.socket.client.Socket
        lateinit var address : String
        lateinit var port : String

        init
        {
            this.address = address
            this.port = port
        }
        override fun run() {
            super.run()
            if(address != "" && port!="")
            {
                var fullAddr : String = "http://"+address+":"+port
                try{
                    msocket = IO.socket(fullAddr)
                }catch(e:Exception){
                    Log.e("error",e.printStackTrace().toString())
                }
                msocket.connect()
                if(msocket.connected())
                {
                    Log.d("connect status","연결 성공")
                }
            }
            else
            {
                Log.e("connect error","연결 에러")
            }
        }
    }
}