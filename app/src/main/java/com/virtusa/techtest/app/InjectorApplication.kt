package com.virtusa.techtest.app

import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class InjectorApplication : DaggerApplication() {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun applicationInjector(): AndroidInjector<InjectorApplication> = appComponent

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
}