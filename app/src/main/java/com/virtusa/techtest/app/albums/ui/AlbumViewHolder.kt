package com.virtusa.techtest.app.albums.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.virtusa.techtest.app.albums.Album
import kotlinx.android.synthetic.main.album_item_view.view.*

class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populate(value: Album) {
        itemView.album_item_view_title.text = value.name
    }
}