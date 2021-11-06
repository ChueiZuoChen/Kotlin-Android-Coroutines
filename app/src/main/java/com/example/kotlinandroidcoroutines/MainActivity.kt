package com.example.kotlinandroidcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG:String = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //if during coroutine executing, when the main thread is destroyed
        //then the coroutine will be cancel immediately!!
        GlobalScope.launch {
            delay(5000L)
            Log.d(TAG, "Thread on Global Scope ${Thread.currentThread().name}")
        }

        Log.d(TAG, "Thread on MainActivity ${Thread.currentThread().name}")
    }
}