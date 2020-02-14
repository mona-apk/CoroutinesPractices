package com.kamiapk.coroutinespractices.model

import retrofit2.http.GET
import retrofit2.http.Path

//retrofit2.6↑ でsuspending functionの記述が可能になる
interface AlbumService {
    @GET("/posts/{id}")
    suspend fun getAlbum(@Path(value = "id") albumID : Int) : Albums
}