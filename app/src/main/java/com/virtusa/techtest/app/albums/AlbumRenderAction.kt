package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxRenderAction
import com.virtusa.techtest.api.AlbumJson

sealed class AlbumRenderAction : MxRenderAction {
    object OnProgress : AlbumRenderAction()
    object OnError : AlbumRenderAction()
    data class ShowAlbums(val albums: List<AlbumJson>) : AlbumRenderAction()
}