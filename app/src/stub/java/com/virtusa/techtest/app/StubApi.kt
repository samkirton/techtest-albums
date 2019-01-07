package com.virtusa.techtest.app

import android.content.Context
import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response
import java.util.*

class StubApi(private val context: Context) {

    fun handle(
        stub: Stub,
        request: Request
    ): Response = when (stub) {
        is Stub.Success -> {
            Response.Builder().create(request, 200, readFile("stub/albums.json", context))
        }
        Stub.GenericError -> {
            errorResponses.pop()
        }
    }

    private val errorResponses: LinkedList<Response> = with(LinkedList<Response>()) {
        add(Response.Builder().create(
            Request.Builder().url("http://stub").build(), 400, ""))
        add(Response.Builder().create(
            Request.Builder().url("http://stub").build(), 200, readFile("stub/albums.json", context)))
        this
    }

    private fun Response.Builder.create(request: Request, code: Int, body: String): Response {
        this.request(request)
        this.protocol(okhttp3.Protocol.HTTP_2)
        this.message("stub")
        this.code(code)
        this.body(okhttp3.ResponseBody.create(null, body))
        this.headers(Headers.Builder().build())
        return this.build()
    }

    private fun readFile(fileName: String, context: Context): String {
        return context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}