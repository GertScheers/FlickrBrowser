package com.gitje.flickrbrowser

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
import kotlinx.android.synthetic.main.browse.view.*

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail : ImageView = view.findViewById(R.id.thumbnail)
    var title : TextView = view.findViewById(R.id.title)
}

class FlickrRecyclerViewAdapter(private var photoList: List<Photo>) : RecyclerView.Adapter<FlickrImageViewHolder>() {
private val TAG = "FlickrRecyclerAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        Log.d(TAG, "onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        if(photoList.isEmpty()){
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.text = "No photos match your search.\n\nUse the search icon to search for photo's."
        } else {
            //Called by the layout manager when it wants new data in an existing view
            val photoItem = photoList[position]
            //Log.d(TAG, "onBindViewHolder: ${photoItem.title} --> $position")
            Picasso.get().load(photoItem.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)

            holder.title.text = photoItem.title
        }
    }

    override fun getItemCount(): Int {
        //Log.d(TAG, "getItemCount called")
        if(photoList.isNotEmpty()) return photoList.size else return 1
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