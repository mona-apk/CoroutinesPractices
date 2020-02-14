package com.kamiapk.coroutinespractices.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.kamiapk.coroutinespractices.model.AlbumsRepository
import com.kamiapk.coroutinespractices.util.UIEventManager
import retrofit2.HttpException
import java.io.IOException
import kotlin.random.Random


class Demo4ViewModel(application: Application, private val eventManager : UIEventManager) : BaseViewModel(application) {

    private val id = Random.nextInt(9) + 1
    private val albumsRepository = AlbumsRepository()

    val albums =  liveData {
        Log.d("tags",id.toString())
        try {
            eventManager.viewProgressBar()
            eventManager.viewToast("")
            val album = albumsRepository.getAlbum(id)
            eventManager.stopProgressBar()
            emit(album)
        }catch (ioException : IOException){
            eventManager.viewToast("Network Error")
            eventManager.stopProgressBar()
        }catch (httpException : HttpException) {
            eventManager.viewToast("Error")
            eventManager.stopProgressBar()
        }
    }

}

//ViewModelに引数を渡す場合,
//ViewModelProvider.Factoryを継承した自作クラスを用意
class Demo4ViewModelFactory(private val application: Application, private val eventManager : UIEventManager) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Demo4ViewModel::class.java)){
            return Demo4ViewModel(application, eventManager) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}