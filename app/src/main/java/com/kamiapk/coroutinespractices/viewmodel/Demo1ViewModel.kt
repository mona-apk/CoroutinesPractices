package com.kamiapk.coroutinespractices.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Demo1ViewModel(application: Application) : BaseViewModel(application) {

    private var cCount = 0
    var cText = MutableLiveData<String>().apply {
        value = cCount.toString()
    }

    fun coroutineCount() {
        /*
        ViewModelが生きている限り他のアプリを起動していても動き続ける
         */
        launch {
            val threadInfo : String = "Thread Name : " + Thread.currentThread().name
            Log.d("TAG",threadInfo)
            for( i in 1..100) {
                cCount++
                Log.d("TAG",cCount.toString())
                cText.value = cCount.toString()
                delay(500)
            }
        }
    }

}