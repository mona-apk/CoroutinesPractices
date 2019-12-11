package com.kamiapk.coroutinespractices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_check_curren_thread.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Demo2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_curren_thread)

        CoroutineScope(Dispatchers.Main).launch {
            val threadInfo : String = "Thread Name : " + Thread.currentThread().name
            main.text = threadInfo
        }

        CoroutineScope(Dispatchers.Default).launch {
            val threadInfo : String = "Thread Name : " + Thread.currentThread().name
            default_.text = threadInfo
        }

        CoroutineScope(Dispatchers.IO).launch {
            val threadInfo : String = "Thread Name : " + Thread.currentThread().name
            io.text = threadInfo
        }

        CoroutineScope(Dispatchers.Unconfined).launch {
            val threadInfo : String = "Thread Name : " + Thread.currentThread().name
            unconfined.text = threadInfo
        }

        start_button.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                val threadInfo : String = "Thread Name : " + Thread.currentThread().name
                main.text = threadInfo
            }

            //TODO:なぜUIを変えることができるのかについて調べる
            CoroutineScope(Dispatchers.Default).launch {
                val threadInfo : String = "Thread Name : " + Thread.currentThread().name
                default_.text = threadInfo
            }

            //TODO:なぜUIを変えることができるのかについて調べる
            CoroutineScope(Dispatchers.IO).launch {
                //実際demo1の場合は変えることができていない
                val threadInfo : String = "Thread Name : " + Thread.currentThread().name
                io.text = threadInfo
            }

            CoroutineScope(Dispatchers.Unconfined).launch {
                val threadInfo : String = "Thread Name : " + Thread.currentThread().name
                unconfined.text = threadInfo
            }



        }

        explain_text.text = "画面表示&ボタンタップでコールーチンを生成 \n コールーチンが動いているスレッドの情報を取得し表示"

    }
}
