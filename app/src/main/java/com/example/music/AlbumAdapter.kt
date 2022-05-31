package com.example.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(private val albumList : List<Album>) : RecyclerView.Adapter<AlbumAdapter
.AlbumViewHolder>() {
    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val albumImageView : ImageView = itemView.findViewById(R.id.imageViewAlbum)
        val albumName : TextView = itemView.findViewById(R.id.imageViewAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]
        holder.albumImageView.setImageResource(album.albumImage)
        holder.albumName.text = album.albumName
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}