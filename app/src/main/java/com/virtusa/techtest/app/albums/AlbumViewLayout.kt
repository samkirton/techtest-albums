package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxViewLayout

interface AlbumViewLayout : MxViewLayout {
    fun showProgress()
    fun showError()
    fun showAlbums(albums: List<Album>)
}