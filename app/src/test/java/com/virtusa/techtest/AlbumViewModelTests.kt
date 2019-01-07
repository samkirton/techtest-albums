package com.virtusa.techtest

import android.os.NetworkOnMainThreadException
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.virtusa.techtest.api.AlbumJson
import com.virtusa.techtest.api.Api
import com.virtusa.techtest.app.albums.AlbumIntent
import com.virtusa.techtest.app.albums.AlbumViewModel
import com.virtusa.techtest.app.albums.AlbumViewState
import com.virtusa.techtest.util.TestRxScheduler
import com.virtusa.techtest.util.get
import io.reactivex.Observable
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import retrofit2.Response
import java.util.Arrays.asList

@RunWith(JUnitPlatform::class)
class AlbumViewModelTests : Spek({

    given("AlbumViewModel") {

        val api by memoized { mock<Api>() }
        val testRxScheduler by memoized { TestRxScheduler() }
        val viewModel by memoized { AlbumViewModel(api, testRxScheduler, mock()) }

        on("Retrieved albums successfully") {

            val albums = asList(mock<AlbumJson>())
            val response = mock<Response<List<AlbumJson>>> {
                on {
                    isSuccessful
                }.doReturn(true)

                on {
                    body()
                }.doReturn(albums)
            }

            whenever(api.getAlbums()).thenReturn(Single.just(response))

            viewModel.processIntents(Observable.just(AlbumIntent.Init))
            val states = viewModel.states().test()

            it ("should display a list of albums") {
                assertEquals(AlbumViewState.View.ShowAlbums, states.get(0).view)
                assertEquals(albums, states.get(0).albums)
            }
        }

        on("Failed to retrieve albums") {

            whenever(api.getAlbums()).thenReturn(Single.error(NetworkOnMainThreadException()))

            viewModel.processIntents(Observable.just(AlbumIntent.Init))
            val states = viewModel.states().test()

            it ("should display an error message") {
                assertEquals(AlbumViewState.View.OnError, states.get(0).view)
            }
        }
    }
})