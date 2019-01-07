package com.virtusa.techtest.app

sealed class Stub {
    object Success : Stub()
    object GenericError : Stub()
}