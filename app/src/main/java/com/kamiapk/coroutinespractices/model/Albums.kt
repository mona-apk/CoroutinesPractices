package com.kamiapk.coroutinespractices.model


import com.squareup.moshi.Json

data class Albums(
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: Int
)