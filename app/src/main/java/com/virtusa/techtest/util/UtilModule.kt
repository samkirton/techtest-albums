package com.virtusa.techtest.util

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
internal object UtilModule {

    @JvmStatic
    @Provides
    fun rxScheduler(): RxScheduler {
        return object : RxScheduler {
            override fun main(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

            override fun thread(): Scheduler {
                return Schedulers.io()
            }
        }
    }
}