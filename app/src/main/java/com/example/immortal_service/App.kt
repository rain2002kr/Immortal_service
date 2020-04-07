package com.example.immortal_service
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.*

// Create a custom application class extending from Application
// and implementing the LifeCycleDelegate interface
// Remember to set the name of your application to you custom Application class in your manifest
// <application
//  android:name=".App"
//    ....
class App : Application(), LifeCycleDelegate {
    val TAG :String = "App"
    var appInBackGround :Boolean = false

    override fun onCreate() {
        super.onCreate()
        val lifeCycleHandler = AppLifecycleHandler(this)
        registerLifecycleHandler(lifeCycleHandler)
    }

    override fun onAppBackgrounded() {
        Log.d(TAG, "App in background")
        appInBackGround = true
    }

    override fun onAppForegrounded() {
        Log.d(TAG, "App in foreground")
        appInBackGround = false
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        if(appInBackGround == true) {
            val intent = Intent(applicationContext, MyService::class.java)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
            intent.putExtra("command", "onAppStoppedRestart")
            startService(intent)
        }
    }

    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }
}

/**
 * If you are using Android Architecture Components you can use the ProcessLifecycleOwner
 * and LifecycleObserver like so (set this class to the app name in the manifest)
 * // <application
//   android:name=".ArchLifecycleApp"
//    ....
 */
class ArchLifecycleApp(): Application(), LifecycleObserver {
    val TAG :String = "ArchLifecycleApp"

    lateinit var lifeCycle :Lifecycle
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        lifeCycle  = ProcessLifecycleOwner.get().lifecycle
        Log.d(TAG, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        Log.d(TAG, "onStart")
        //Log.d(TAG, "App in foreground_Life")
        Log.d(TAG, "onStart")
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            Log.d(TAG, "currentState is greater or equal to INITIALIZED")
        }
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            Log.d(TAG, "currentState is greater or equal to CREATED")
        }
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Log.d(TAG, "currentState is greater or equal to STARTED")
        }
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            Log.d(TAG, "currentState is greater or equal to RESUMED")
        }
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.d(TAG, "currentState is greater or equal to DESTROYED")
        }
    }


    //temp
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(TAG, "onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            Log.d(TAG, "currentState is greater or equal to RESUMED")
        }
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.d(TAG, "currentState is greater or equal to DESTROYED")
        }
        Log.d(TAG, "onDestroy")
        /*
         val intent = Intent(applicationContext, MyService::class.java)
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                 Intent.FLAG_ACTIVITY_SINGLE_TOP or
                 Intent.FLAG_ACTIVITY_CLEAR_TOP
         )
         intent.putExtra("command", "onAppStoppedRestart_Life")
         startService(intent)
         */
    }

    //temp
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d(TAG, "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            Log.d(TAG, "currentState is greater or equal to RESUMED")
        }
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.d(TAG, "currentState is greater or equal to DESTROYED")
        }
        Log.d(TAG, "onStop")
        //Log.d(TAG, "App in background1_Life")

    }




}