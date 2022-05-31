package com.IoTProject.canseeapp

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketClass() {
    companion object {
        private lateinit var socket : Socket
        private lateinit var address : String
        private lateinit var port : String
        private var status : String = "connect"
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
    }


}