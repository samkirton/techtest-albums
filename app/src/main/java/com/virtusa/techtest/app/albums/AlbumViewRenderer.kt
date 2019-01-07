package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxViewRenderer
import javax.inject.Inject

class AlbumViewRenderer @Inject internal constructor() : MxViewRenderer<AlbumViewLayout, AlbumViewState> {
    override fun layout(layout: AlbumViewLayout, state: AlbumViewState): Unit = when (state.view) {
        AlbumViewState.View.Idle -> {
        }
        AlbumViewState.View.OnProgress -> {
            layout.showProgress()
        }
        AlbumViewState.View.OnError -> {
            layout.showError()
        }
        AlbumViewState.View.ShowAlbums -> {
            layout.showAlbums(state.albums)
        }
    }
}