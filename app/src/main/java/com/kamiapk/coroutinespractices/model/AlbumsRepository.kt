package com.kamiapk.coroutinespractices.model

class AlbumsRepository {

    var albumService : AlbumService = RetrofitInstance.albumService
    suspend fun getAlbum(albumId : Int) : Albums {
        return albumService.getAlbum(albumId)
    }

}