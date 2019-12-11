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

        launch {
            for( i in 1..100) {
                cCount++
                Log.d("TAG",cCount.toString())
                cText.value = cCount.toString()
                delay(500)
            }
        }

    }


}