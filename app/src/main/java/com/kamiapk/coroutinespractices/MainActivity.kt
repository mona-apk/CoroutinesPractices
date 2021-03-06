package com.kamiapk.coroutinespractices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Demo1Activity
        button1.setOnClickListener {
            val intent = Intent(this,Demo1Activity::class.java)
            startActivity(intent)
        }

        //Demo2
        demo2.setOnClickListener {
            val intent = Intent(this,Demo2Activity::class.java)
            startActivity(intent)
        }

        //Demo3
        demo3.setOnClickListener {
            val intent = Intent(this,Demo3Activity::class.java)
            startActivity(intent)
        }

        //Demo3
        demo4.setOnClickListener {
            val intent = Intent(this,Demo4Activity::class.java)
            startActivity(intent)
        }



    }
}
