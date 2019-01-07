package com.virtusa.techtest.db.dao

import com.virtusa.techtest.db.AlbumDao
import com.virtusa.techtest.util.RxScheduler
import io.reactivex.Single
import javax.inject.Inject

class CountAlbums @Inject internal constructor(
    private val albumDao: AlbumDao,
    private val rx: RxScheduler
) {

    fun count(): Single<Int> {
        return Single.fromCallable { albumDao.count() }
            .observeOn(rx.main())
            .subscribeOn(rx.thread())
    }
}