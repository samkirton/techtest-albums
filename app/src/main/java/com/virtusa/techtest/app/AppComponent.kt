package com.virtusa.techtest.app

import android.app.Application
import com.virtusa.techtest.api.ApiModule
import com.virtusa.techtest.util.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        AppModule::class,
        UtilModule::class
    ]
)
interface AppComponent : AndroidInjector<InjectorApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}