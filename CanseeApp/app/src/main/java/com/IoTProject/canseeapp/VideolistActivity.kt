package com.IoTProject.canseeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ListView
import java.net.Socket

class VideolistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.videolist_layout)
        Log.d("SocketStatus","소켓의 상태 : "+SocketClass.getSocketStatus().toString())
        Log.d("videoList","비디오 리스트 항목 : "+SocketClass.videoList.toString())

        var videolistView : ListView = findViewById(R.id.videoListView)

        val videoAdapter = VideolistAdapter(this,SocketClass.videoList)
        videolistView.adapter = videoAdapter

        videolistView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            // Log.d("Check",SocketClass.videoList[position])
            SocketClass.socket.emit("PlayVideo",SocketClass.videoList[position])
            SocketClass.callbackVideoPlay(ReqProcess={
                Log.d("Ready?","동영상 준비됨?")
            })
        })


    }
}