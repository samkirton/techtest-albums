package com.virtusa.techtest.app.albums

import androidx.lifecycle.ViewModel
import com.virtusa.techtest.app.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AlbumActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    internal abstract fun contributesAlbumViewModel(viewModel: AlbumViewModel): ViewModel
}