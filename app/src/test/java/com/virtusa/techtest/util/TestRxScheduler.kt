package com.virtusa.techtest.util

import io.reactivex.schedulers.Schedulers

class TestRxScheduler : RxScheduler {
    override fun main() = Schedulers.trampoline()
    override fun thread() = Schedulers.trampoline()
}