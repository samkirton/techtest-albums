package com.virtusa.techtest.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("albums")
    fun getAlbums(): Single<Response<List<AlbumJson>>>
}