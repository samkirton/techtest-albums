package com.virtusa.techtest.db.dao

import com.virtusa.techtest.db.AlbumDao
import com.virtusa.techtest.db.AlbumEntity
import com.virtusa.techtest.util.RxScheduler
import io.reactivex.Single
import javax.inject.Inject

class GetAlbums @Inject internal constructor(
    private val albumDao: AlbumDao,
    private val rx: RxScheduler
) {

    fun select(): Single<List<AlbumEntity>> {
        return Single.fromCallable { albumDao.getAlbums() }
            .observeOn(rx.main())
            .subscribeOn(rx.thread())
    }
}