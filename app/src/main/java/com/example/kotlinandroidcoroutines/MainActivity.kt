package com.example.kotlinandroidcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinandroidcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculation")
            for (i in 30..40){
                Log.d(TAG, "Result for $i = ${fib(i)}")
            }
            Log.d(TAG, "Finished long running calculation")
        }
        //runBLocking it will block current thread, in here which is main thread,
        //and it will block main thread until job is finished
        runBlocking {
            delay(2000L)
            job.cancel()
            Log.d(TAG, "Main Thread is continuing...")
        }*/

        //use withTimeout()
        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculation")
            withTimeout(1000L) {
                for (i in 30..50) {
                    if (isActive) {
                        Log.d(TAG, "Result for $i = ${fib(i)}")
                    }
                }
            }
            Log.d(TAG, "Finished long running calculation")
        }
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "doNetWorkResult"
    }

    fun fib(n: Int): Long {
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fib(n - 1) + fib(n - 2)
        }
    }

}