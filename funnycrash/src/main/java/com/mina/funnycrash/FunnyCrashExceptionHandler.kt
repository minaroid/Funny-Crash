package com.mina.funnycrash

import android.content.Intent
import android.os.Looper
import android.util.Log

class FunnyCrashExceptionHandler : Thread.UncaughtExceptionHandler {

    private val exceptionHandler: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, throwable: Throwable) {

        Log.d("minaCrash", throwable.localizedMessage)


//      CrashUtil.saveCrashReport(throwable);


        exceptionHandler?.uncaughtException(thread, throwable)

    }


    private fun isMainThread() = Looper.getMainLooper().thread == Thread.currentThread()
}