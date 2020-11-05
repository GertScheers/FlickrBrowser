package com.gitje.flickrbrowser

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.browse.view.*

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail : ImageView = view.findViewById(R.id.thumbnail)
    var totme : TextView = view.findViewById(R.id.title)
}

class FlickrRecyclerViewAdapter(private var photoList: List<Photo>) : RecyclerView.Adapter<FlickrImageViewHolder>() {
private val TAG = "FlickrRecyclerAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        Log.d(TAG, "onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount called")
        if(photoList.isNotEmpty()) return photoList.size else return 0
    }

    fun loadNewData(newPhotos: List<Photo>){
        photoList = newPhotos
        //Refresh data
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int) : Photo? {
        if(photoList?.size >= position) return photoList[position] else return null
    }
}