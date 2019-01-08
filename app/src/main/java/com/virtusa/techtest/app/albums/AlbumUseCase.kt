package com.virtusa.techtest.app.albums

import com.virtusa.techtest.api.AlbumJson
import com.virtusa.techtest.api.Api
import com.virtusa.techtest.db.AlbumEntity
import com.virtusa.techtest.db.dao.CountAlbums
import com.virtusa.techtest.db.dao.GetAlbums
import com.virtusa.techtest.db.dao.InsertAlbums
import com.virtusa.techtest.util.RxScheduler
import io.reactivex.Single
import javax.inject.Inject

class AlbumUseCase @Inject internal constructor(
    private val api: Api,
    private val countAlbums: CountAlbums,
    private val getAlbums: GetAlbums,
    private val insertAlbums: InsertAlbums,
    private val rx: RxScheduler
) {

    fun getAlbums(): Single<List<Album>> {
        return countAlbums.count().flatMap { albumCount ->
            if (albumCount > 0) {
                getAlbumEntities().map {
                    it.sortedBy { it.name }
                }
            } else {
                fetchAlbums().flatMap { albums ->
                    insertAlbumJson(albums).map {
                        it.sortedBy { it.name }
                    }
                }
            }
        }
    }

    private fun getAlbumEntities(): Single<List<Album>> {
        return getAlbums.select().map { albums ->
            albums.map { Album(it.name) }
        }
    }

    private fun fetchAlbums(): Single<List<AlbumJson>> {
        return api.getAlbums().observeOn(rx.main()).subscribeOn(rx.thread()).map { response ->
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        }
    }

    private fun insertAlbumJson(albums: List<AlbumJson>): Single<List<Album>> {
        return if (albums.isNotEmpty()) {
            insertAlbums.insert(albums.map { albumJson ->
                AlbumEntity(albumJson.title)
            }).map { albumEntities ->
                albumEntities.map { albumEntity ->
                    Album(albumEntity.name)
                }
            }
        } else {
            Single.just(emptyList())
        }
    }
}