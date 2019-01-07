package com.virtusa.techtest.app.albums

import android.os.Bundle
import com.virtusa.techtest.R
import com.virtusa.techtest.app.MviActivity
import com.virtusa.techtest.app.ViewModelFactory
import com.virtusa.techtest.app.albums.ui.AlbumAdapter
import com.virtusa.techtest.uikit.gone
import com.virtusa.techtest.uikit.visible
import dagger.android.AndroidInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.album_activity.*
import javax.inject.Inject

class AlbumActivity
    : MviActivity<AlbumIntent, AlbumRenderAction, AlbumViewState, AlbumViewLayout>(), AlbumViewLayout {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var render: AlbumViewRenderer

    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_activity)
        albumAdapter = AlbumAdapter(this)
        album_activity_recyclerView.adapter = albumAdapter
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun intents(): Observable<AlbumIntent> = Observable.merge(
        Observable.just(AlbumIntent.Init),
        album_activity_errorRetryView.retryClick().map {
            AlbumIntent.Retry
        }
    )

    override fun layout(): AlbumViewLayout = this

    override fun model(): AlbumViewModel = getViewModel(viewModelFactory)

    override fun render(): AlbumViewRenderer = render

    override fun showProgress() {
        album_activity_progressBar.visible()
        album_activity_errorRetryView.gone()
        album_activity_recyclerView.gone()
    }

    override fun showError() {
        album_activity_progressBar.gone()
        album_activity_errorRetryView.visible()
        album_activity_errorRetryView.populate(getString(R.string.album_error))
    }

    override fun showAlbums(albums: List<Album>) {
        album_activity_progressBar.gone()
        album_activity_recyclerView.visible()
        albumAdapter.populate(albums)
    }
}
