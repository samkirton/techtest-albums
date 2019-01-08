package com.virtusa.techtest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.virtusa.techtest.api.AlbumJson
import com.virtusa.techtest.api.Api
import com.virtusa.techtest.app.albums.AlbumUseCase
import com.virtusa.techtest.db.AlbumEntity
import com.virtusa.techtest.db.dao.CountAlbums
import com.virtusa.techtest.db.dao.GetAlbums
import com.virtusa.techtest.db.dao.InsertAlbums
import com.virtusa.techtest.util.TestRxScheduler
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import retrofit2.Response
import java.util.Arrays.asList

@RunWith(JUnitPlatform::class)
class AlbumUseCaseTests : Spek({

    given("AlbumUseCase") {

        val api by memoized { mock<Api>() }
        val countAlbums by memoized { mock<CountAlbums>() }
        val getAlbums by memoized { mock<GetAlbums>() }
        val insertAlbums by memoized { mock<InsertAlbums>() }

        val albumUseCase by memoized {
            AlbumUseCase(api, countAlbums, getAlbums, insertAlbums, TestRxScheduler())
        }

        on("Albums do not exists in the database, successfully load albums from the network") {
            whenever(countAlbums.count()).thenReturn(Single.just(0))

            val albumJson = AlbumJson("1", "1", "Illmatic")
            val albumList = asList(albumJson)
            val albumResponse = mock<Response<List<AlbumJson>>> {
                on {
                    isSuccessful
                }.thenReturn(true)

                on {
                    body()
                }.thenReturn(albumList)
            }

            whenever(api.getAlbums()).thenReturn(Single.just(albumResponse))

            whenever(insertAlbums.insert(asList(AlbumEntity("Illmatic"))))
                .thenReturn(Single.just(asList(AlbumEntity("Illmatic"))))

            it("should return albums") {
                val getAlbumsCall = albumUseCase.getAlbums().blockingGet()
                assertEquals("Illmatic", getAlbumsCall[0].name)
            }
        }

        on("Albums do not exists in the database, failed to load albums from the network") {
            whenever(countAlbums.count()).thenReturn(Single.just(0))

            val albumResponse = mock<Response<List<AlbumJson>>> {
                on {
                    isSuccessful
                }.thenReturn(false)
            }

            whenever(api.getAlbums()).thenReturn(Single.just(albumResponse))

            it("should return albums") {
                val getAlbumsCall = albumUseCase.getAlbums().blockingGet()
                assertTrue(getAlbumsCall.isEmpty())
            }
        }

        on("Albums exist in the database") {
            whenever(countAlbums.count()).thenReturn(Single.just(1))
            whenever(getAlbums.select()).thenReturn(Single.just(asList(AlbumEntity("Illmatic"))))

            it("should return albums") {
                val getAlbumsCall = albumUseCase.getAlbums().blockingGet()
                assertEquals("Illmatic", getAlbumsCall[0].name)
            }
        }
    }
})