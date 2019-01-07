package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxViewState
import com.virtusa.techtest.api.AlbumJson

data class AlbumViewState(
    val albums: List<AlbumJson> = emptyList(),
    val view: View
) : MxViewState {

    sealed class View {
        object Idle : View()
        object OnProgress : View()
        object OnError : View()
        object ShowAlbums : View()
    }
}