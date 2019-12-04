package com.kamiapk.coroutinespractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kamiapk.coroutinespractices.databinding.ActivityDemo1Binding
import com.kamiapk.coroutinespractices.viewmodel.Demo1ViewModel
import kotlinx.android.synthetic.main.activity_demo1.*

class Demo1Activity : AppCompatActivity() {

    private lateinit var viewModel : Demo1ViewModel

    private var nCount = 0
    var cCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        //setContentView(R.layout.activity_demo1)

        viewModel = ViewModelProviders.of(this).get(Demo1ViewModel::class.java)
        
        val binding = DataBindingUtil.setContentView<ActivityDemo1Binding>(this, R.layout.activity_demo1)
            .apply{
                lifecycleOwner = this@Demo1Activity // これが超絶必要
                demo1 = viewModel //layoutに記述した<data>タグ内のnameにtypeを渡す
            }
        
        
        n_button.setOnClickListener { 
            nCount++
            n_text.text = nCount.toString()
        }
        
        c_button.setOnClickListener {
            
            /**
                CoroutineScope コールーチンのスコープを提供するインターフェイス
                contextが必要であり、Dispatchers.IOによってccntextが渡される。
                launchはcoroutine builder
                Dispatchers.IOを指定するとUIに関わる操作はできない
                
                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 1..1000) {
                        cCount++
                        Log.d("TAG",cCount.toString())
                        delay(500)
                    }
                }
           
            **/
            viewModel.couroutineCount()
            
        }









    }



}
