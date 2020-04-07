package com.example.immortal_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    val TAG : String ="MyService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        if(intent == null){
            return Service.START_STICKY
        } else {
            processCmd(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
        Log.d(TAG, "onBind()")
    }

    fun processCmd(intent:Intent){
        val command = intent.getStringExtra("command")
        val name = intent.getStringExtra("name")
        Log.d(TAG, "processCmd(), $command, $name")
        //5초 지연후, MainActivity 띄워 줍니다.
        Thread.sleep(5000)
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
        Log.d(TAG, "call mainActivity()")
        startActivity(intent)
    }
}
