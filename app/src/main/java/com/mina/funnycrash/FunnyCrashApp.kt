package com.mina.funnycrash

import android.app.Application


class FunnyCrashApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FunnyCrash.initialize(this)
    }

}