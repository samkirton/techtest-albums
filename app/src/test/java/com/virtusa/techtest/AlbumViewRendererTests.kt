package com.virtusa.techtest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.virtusa.techtest.api.AlbumJson
import com.virtusa.techtest.app.albums.Album
import com.virtusa.techtest.app.albums.AlbumViewLayout
import com.virtusa.techtest.app.albums.AlbumViewRenderer
import com.virtusa.techtest.app.albums.AlbumViewState
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.Arrays.asList

@RunWith(JUnitPlatform::class)
class AlbumViewRendererTests : Spek({

    given("AlbumViewRenderer") {

        on("View.OnProgress") {
            val layout: AlbumViewLayout = mock()
            val renderer = AlbumViewRenderer()

            renderer.layout(layout, AlbumViewState(view = AlbumViewState.View.OnProgress))

            it("layout.showProgress") {
                verify(layout).showProgress()
            }
        }

        on("View.OnError") {
            val layout: AlbumViewLayout = mock()
            val renderer = AlbumViewRenderer()

            renderer.layout(layout, AlbumViewState(view = AlbumViewState.View.OnError))

            it("layout.onError") {
                verify(layout).showError()
            }
        }

        on("View.ShowAlbums") {
            val layout: AlbumViewLayout = mock()
            val renderer = AlbumViewRenderer()
            val albums = asList(mock<Album>())

            renderer.layout(layout, AlbumViewState(view = AlbumViewState.View.ShowAlbums, albums = albums))

            it("layout.showAlbums") {
                verify(layout).showAlbums(albums)
            }
        }
    }
})