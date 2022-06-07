package com.IoTProject.canseeapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class VideolistAdapter(val context : Context, val videolist : MutableMap<Int,String> ) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("adapter","getView 접근")
        var view : View = LayoutInflater.from(context).inflate(R.layout.videolist_item,null)

        var videoimg = view.findViewById<ImageView>(R.id.VideoImageView)
        var videoName = view.findViewById<TextView>(R.id.VideoName)

        val video = videolist
        Log.d("adapter",video.toString())
        videoimg.setImageResource(R.drawable.videoicon)
        videoName.text = video[position]

        return view
    }

    override fun getCount(): Int {
        return videolist.count()
    }


    override fun getItem(position: Int): String? {
        return videolist[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}