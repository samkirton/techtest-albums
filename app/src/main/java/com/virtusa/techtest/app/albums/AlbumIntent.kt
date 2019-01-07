package com.virtusa.techtest.app.albums

import com.memtrip.mxandroid.MxViewIntent

sealed class AlbumIntent : MxViewIntent {
    object Init : AlbumIntent()
    object Retry : AlbumIntent()
}