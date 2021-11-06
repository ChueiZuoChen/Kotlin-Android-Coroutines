package com.example.kotlinandroidcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinandroidcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

const val TAG:String = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Do network call on IO thread, after get the result then update to Main thread UI views
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine result in thread: ${Thread.currentThread().name}")
            val result = doNetworkCall()
            withContext(Dispatchers.Main){
                Log.d(TAG, "Setting result in thread: ${Thread.currentThread().name}")
                binding.tv1.text = result
            }
        }
    }

    suspend fun doNetworkCall():String{
        delay(3000L)
        return "doNetWorkResult"
    }

}