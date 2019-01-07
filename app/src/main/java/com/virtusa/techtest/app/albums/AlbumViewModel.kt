package com.virtusa.techtest.app.albums

import android.app.Application
import com.memtrip.mxandroid.MxViewModel
import com.virtusa.techtest.api.Api
import com.virtusa.techtest.util.RxScheduler
import io.reactivex.Observable
import javax.inject.Inject

class AlbumViewModel @Inject internal constructor(
    private val api: Api,
    private val rx: RxScheduler,
    application: Application
) : MxViewModel<AlbumIntent, AlbumRenderAction, AlbumViewState>(
    AlbumViewState(view = AlbumViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: AlbumIntent): Observable<AlbumRenderAction> = when (intent) {
        is AlbumIntent.Init -> getAlbums()
        AlbumIntent.Retry -> getAlbums()
    }

    override fun reducer(previousState: AlbumViewState, renderAction: AlbumRenderAction): AlbumViewState = when (renderAction) {
        AlbumRenderAction.OnProgress -> previousState.copy(view = AlbumViewState.View.OnProgress)
        AlbumRenderAction.OnError -> previousState.copy(view = AlbumViewState.View.OnError)
        is AlbumRenderAction.ShowAlbums -> previousState.copy(
            view = AlbumViewState.View.ShowAlbums,
            albums = renderAction.albums)
    }

    private fun getAlbums(): Observable<AlbumRenderAction> {
        return api.getAlbums().observeOn(rx.main()).subscribeOn(rx.thread()).map { response ->
            if (response.isSuccessful) {
                AlbumRenderAction.ShowAlbums(response.body()!!)
            } else {
                AlbumRenderAction.OnError
            }
        }.onErrorReturn {
            AlbumRenderAction.OnError
        }.toObservable().startWith(AlbumRenderAction.OnProgress)
    }
}