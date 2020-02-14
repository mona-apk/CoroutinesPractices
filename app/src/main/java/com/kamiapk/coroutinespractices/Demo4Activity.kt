package com.kamiapk.coroutinespractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kamiapk.coroutinespractices.util.UIEventManager
import com.kamiapk.coroutinespractices.viewmodel.Demo4ViewModel
import com.kamiapk.coroutinespractices.viewmodel.Demo4ViewModelFactory
import kotlinx.android.synthetic.main.activity_demo4.*


class Demo4Activity : AppCompatActivity(), UIEventManager {

    lateinit var viewModel : Demo4ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo4)
        //自作クラスを用いてViewModelを取得する.
        val viewModelFactory = Demo4ViewModelFactory(application, this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(Demo4ViewModel::class.java)
        albumsObserver()
    }

    private fun albumsObserver() {
        viewModel.albums.observe(this, Observer {
            Log.d("tags","AAAA")
            fetchText.text = it.title
        })
    }

    override fun viewProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun stopProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun viewToast(message: String) {
        var toastMessage = message
        if (message == ""){
            toastMessage = "通信を開始します"
        }
        Toast.makeText(applicationContext, "toastMessage.", Toast.LENGTH_SHORT).show()
    }

}
