package com.kamiapk.coroutinespractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kamiapk.coroutinespractices.databinding.ActivityDemo1Binding
import com.kamiapk.coroutinespractices.viewmodel.Demo1ViewModel
import kotlinx.android.synthetic.main.activity_demo1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        textView9.text = "左のボタンを押すと0.5秒間隔で数字を1増加させる.\nDataBinding + LiveData + Coroutine + ViewModelにて実装\n"

        n_button.setOnClickListener { 
            nCount++
            n_text.text = nCount.toString()
        }
        
        c_button.setOnClickListener {
            
            /*
                CoroutineScopeはコールーチンのスコープを提供するインターフェイス
                contextが必要であり、Dispatchers.IOによってccntextが渡される。
                launchはcoroutine builderで新しいコールーチンを起動させる
                coroutine builderは他にもasync, produce,runBlockingなどがある
             */

            //Dispatchers.Main
            //Mainスレッドで起動するコールーチン.軽量のタスク利用が推奨されている.UI変更可能
            CoroutineScope(Dispatchers.Main).launch {
                cCount++
                Log.d("TAG",cCount.toString())
                //textView8.text = cCount.toString()
                delay(500)
            }

            //Dispatchers.IO
            //UIに関わる操作はできない.なぜならバックグラウンドスレッドで起動するから.
            CoroutineScope(Dispatchers.IO).launch {
                //CRUDやネットワーク通信,ファイル操作など
                Log.d("TAG","A")
                //android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                //textView8.text = cCount.toString()
                Log.d("TAG","B")
            }

            //Dispatchers.Default
            CoroutineScope(Dispatchers.Default).launch {
                //大きいリストのソートや大量のJSONファイルのパースのようなCPU負荷が大きいタスク処理
            }

            //Dispatchers.Unconfined
            CoroutineScope(Dispatchers.Unconfined).launch {
                //UnconfinedはGlobalScopeで利用される
                //現在のスレッドでコールーチンが走り,停止し再開した場合には
                //再開されたスレッドにてコールーチンが動く
                //Android開発では非推奨
            }

            viewModel.coroutineCount()
        }

    }



}
