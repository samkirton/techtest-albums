package com.virtusa.techtest.app

import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

abstract class BaseInjectorApplication : DaggerApplication() {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun applicationInjector(): AndroidInjector<BaseInjectorApplication> = appComponent

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    abstract val appComponent: AppComponent
}