package com.virtusa.techtest.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import com.virtusa.techtest.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.error_retry_view.view.*

class ErrorRetryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.error_retry_view, this)
    }

    fun populate(body: String) {
        error_retry_view_body.text = body
    }

    fun retryClick(): Observable<Any> = RxView.clicks(error_retry_view_button)
}