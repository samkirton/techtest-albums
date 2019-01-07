package com.virtusa.techtest.app

import com.virtusa.techtest.app.albums.AlbumActivity
import com.virtusa.techtest.app.albums.AlbumActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [AlbumActivityModule::class])
    internal abstract fun contributeAlbumActivity(): AlbumActivity
}