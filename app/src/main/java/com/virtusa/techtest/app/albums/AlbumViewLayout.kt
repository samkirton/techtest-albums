package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxViewLayout
import com.virtusa.techtest.api.AlbumJson

interface AlbumViewLayout : MxViewLayout {
    fun showProgress()
    fun showError()
    fun showAlbums(albums: List<AlbumJson>)
}