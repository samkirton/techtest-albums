package com.virtusa.techtest.app.albums.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virtusa.techtest.R
import com.virtusa.techtest.api.AlbumJson

class AlbumAdapter(
    context: Context,
    private val inflater: LayoutInflater = LayoutInflater.from(context),
    private val data: MutableList<AlbumJson> = ArrayList()
) : RecyclerView.Adapter<AlbumViewHolder>() {

    fun populate(albumJson: List<AlbumJson>) {
        data.clear()
        data.addAll(albumJson)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(inflater.inflate(R.layout.album_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.populate(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}