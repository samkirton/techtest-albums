package com.virtusa.techtest

import android.app.Application
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.virtusa.techtest.api.ApiConfig
import com.virtusa.techtest.app.DaggerAppComponent
import com.virtusa.techtest.app.Stub
import com.virtusa.techtest.app.StubInterceptor
import com.virtusa.techtest.app.albums.AlbumActivity
import java.util.*

class StubActivityTestRule : ActivityTestRule<AlbumActivity>(
    AlbumActivity::class.java,
    true,
    false
) {

    private lateinit var stub: Stub

    fun launch(stub: Stub) {
        this.stub = stub
        launchActivity(null)
    }

    private fun inject() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        val injector = DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig(Arrays.asList(StubInterceptor(stub, context))))
            .application(context)
            .build()

        TestInjectorApplication.setInjector(injector)
    }

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        inject()
        InstrumentationRegistry.getTargetContext().deleteDatabase("techtest_db")
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        TestInjectorApplication.resetInjector()
    }
}