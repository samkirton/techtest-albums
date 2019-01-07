package com.virtusa.techtest.db.dao

import com.virtusa.techtest.db.AlbumDao
import com.virtusa.techtest.db.AlbumEntity
import com.virtusa.techtest.util.RxScheduler
import io.reactivex.Single
import javax.inject.Inject

class InsertAlbums @Inject internal constructor(
    private val albumDao: AlbumDao,
    private val rx: RxScheduler
) {

    fun insert(albumEntities: List<AlbumEntity>): Single<List<AlbumEntity>> {
        return Single.fromCallable {
            albumDao.insertAll(albumEntities)
            albumEntities
        }.observeOn(rx.main()).subscribeOn(rx.thread())
    }
}