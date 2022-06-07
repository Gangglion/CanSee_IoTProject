package com.IoTProject.canseeapp

import android.os.Handler
import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONArray
import java.net.URISyntaxException
import java.util.Objects
import kotlin.reflect.typeOf

class SocketClass() {
    companion object {
        lateinit var socket : Socket
        private lateinit var address : String
        private lateinit var port : String
        lateinit var jsonArray: JSONArray
        var videoList : MutableMap<Int,String> = mutableMapOf(0 to "")

        fun get(address: String,port: String): Socket {
            this.address = address
            this.port = port
            var fulladdr : String = "http://"+address+":"+port
            try {
                socket = IO.socket(fulladdr)
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            return socket
        }
        fun getSocketStatus():Boolean{
            return socket.connected()
        }
        fun SocketDisconnect(){
            socket.disconnect()
        }

        private val requestFileArray : Emitter.Listener = Emitter.Listener{
            Log.d("Socket Response","서버로부터 받은 파일 목록 : "+it[0])
            Log.d("data type","받은 데이터 타입"+it[0]::class.java.simpleName)
            jsonArray = it[0] as JSONArray
            videoList.remove(0)
            for(i in 0 until jsonArray.length()){
                videoList.put(i,jsonArray.get(i).toString())
                Log.d("jsonCheck",jsonArray.get(i).toString())
            }
        }
        fun callbackVideolist(ProcessRes:()->Unit){
            // 서버에서 ResFilelist 이벤트로 보낸 데이터 수신
            socket.on("ResFilelist",requestFileArray)
            Handler().postDelayed({
                ProcessRes()
            },1500L)
        }
        private val responseVideo : Emitter.Listener = Emitter.Listener{
            Log.d("Socket Response","서버로부터 받은 스트림 목록 : "+it)

        }
        fun callbackVideoPlay(ReqProcess:()->Unit){
            // 서버에서 동영상을 받으면 준비완료
            socket.on("Send",responseVideo)
            Handler().postDelayed({
                ReqProcess()
            },1000L)
        }
    }
}