package com.kamiapk.coroutinespractices.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Demo1ViewModel(application: Application) : BaseViewModel(application) {

    private var cCount = 0
    var cText = MutableLiveData<String>().apply {
        value = cCount.toString()
    }

    fun couroutineCount() {

        launch {
            for( i in 1..100) {
                cCount++
                cText.value = cCount.toString()
                delay(500)
            }
        }


        //UIに関わる操作はできない.なぜなら…
        //アプリがバックグラウンドにいっても動き続ける
        CoroutineScope(Dispatchers.IO).launch {
            for( i in 1..100) {
                Log.d("TAG",cCount.toString())
                delay(500)
            }
        }
    }


}