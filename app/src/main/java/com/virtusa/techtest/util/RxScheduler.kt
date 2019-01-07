package com.virtusa.techtest.util

import io.reactivex.Scheduler

interface RxScheduler {
    fun main(): Scheduler
    fun thread(): Scheduler
}