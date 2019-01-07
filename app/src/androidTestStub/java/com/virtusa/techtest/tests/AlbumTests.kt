package com.virtusa.techtest.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.virtusa.techtest.R
import com.virtusa.techtest.StubActivityTestRule
import com.virtusa.techtest.app.Stub
import com.virtusa.techtest.utils.atPosition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumTests {

    @get:Rule
    var rule = StubActivityTestRule()

    @Test
    fun successLoadingAlbums() {
        rule.launch(Stub.Success)

        onView(withId(R.id.album_activity_recyclerView))
            .check(matches(atPosition(0, hasDescendant(ViewMatchers.withText("quidem molestiae enim")))))
            .check(matches(atPosition(1, hasDescendant(ViewMatchers.withText("sunt qui excepturi placeat culpa")))))
            .check(matches(atPosition(2, hasDescendant(ViewMatchers.withText("omnis laborum odio")))))
    }

    @Test
    fun errorLoadingAlbumsSuccessOnRetry() {
        rule.launch(Stub.GenericError)

        onView(withId(R.id.album_activity_errorRetryView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.error_retry_view_button))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.album_activity_recyclerView))
            .check(matches(atPosition(0, hasDescendant(ViewMatchers.withText("quidem molestiae enim")))))
            .check(matches(atPosition(1, hasDescendant(ViewMatchers.withText("sunt qui excepturi placeat culpa")))))
            .check(matches(atPosition(2, hasDescendant(ViewMatchers.withText("omnis laborum odio")))))
    }
}
