package com.virtusa.techtest.app

import com.virtusa.techtest.api.ApiConfig

class InjectorApplication : BaseInjectorApplication() {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig())
            .application(this)
            .build()
    }
}