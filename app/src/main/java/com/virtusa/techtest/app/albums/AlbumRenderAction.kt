package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxRenderAction

sealed class AlbumRenderAction : MxRenderAction {
    object OnProgress : AlbumRenderAction()
    object OnError : AlbumRenderAction()
    data class ShowAlbums(val albums: List<Album>) : AlbumRenderAction()
}