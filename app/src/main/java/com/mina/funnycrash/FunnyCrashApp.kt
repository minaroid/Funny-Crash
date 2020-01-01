package com.mina.funnycrash

import android.app.Application
import android.util.Log
import com.instabug.library.Instabug


class FunnyCrashApp : Application() {


    override fun onCreate() {
        super.onCreate()
//        FunnyCrash.initialize(this)

        Instabug.Builder(this, "6349b3e6ab814fa47a9e584e22039060").build()

    }


    override fun onTerminate() {

        Log.d("minaCrash","fwefefwfwe")

        super.onTerminate()
    }
}