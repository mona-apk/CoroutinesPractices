package com.kamiapk.coroutinespractices

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_demo3.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Demo3Activity : AppCompatActivity() {

    private var nowTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo3)

        normal.setOnClickListener {
            outputText.text = "Normal Launch\n"
            CoroutineScope(IO).launch{
                //逐次処理
                val buf1 = first()
                val buf2 = second()
                outputText.text ="${outputText.text.toString()}\nSum:${buf1 + buf2}s"
            }
        }

        //Async and Await Launch
        asyncButton.setOnClickListener {
            outputText.text = "Async and Await Launch\n"
            CoroutineScope(IO).launch{
                //asyncで囲むだけ
                val buf1 = async {first()}
                val buf2 = async {second()}
                // val x = buf1.await() + buf2.await() のような使われかたをする
                outputText.text ="${outputText.text.toString()}\nSum:${buf2.await()}s"
            }
        }

        demo3Text.text = ""

    }


    //これらの関数は処理が終わるまでに3秒,5秒かかるものと仮定する
    private suspend fun first() : Int{
        val time =  3
        val functionName = "first"
        funcStart(functionName)
        delay(time.toLong() * 1000L)
        funcEnd(functionName, time.toString())
        return time
    }

    private suspend fun second() : Int{
        val time =  5
        val functionName = "second"
        funcStart(functionName)
        delay(time.toLong() * 1000L)
        funcEnd(functionName, time.toString())
        return time
    }

    @SuppressLint("SetTextI18n")
    fun funcStart(funName : String) {
        outputText.text ="${outputText.text.toString()}${funName} function start.\n"
    }

    @SuppressLint("SetTextI18n")
    fun funcEnd(funName : String, s : String) {
        outputText.text ="${outputText.text.toString()}${funName} function end:${s}s\n"
    }


}
