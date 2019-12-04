package com.kamiapk.coroutinespractices

import android.content.Context
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


















    }
}
