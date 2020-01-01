package com.mina.funnycrash

import android.content.Context
import android.content.Intent

object FunnyCrash {

    private var applicationContext: Context? = null

    private var funnyCrashPath: String? = null

    var isNotificationEnabled = true

    fun initialize(context: Context?) {
        applicationContext = context
        setUpExceptionHandler()
    }

    fun initialize(context: Context?, crashSavePath: String?) {
        applicationContext = context
        funnyCrashPath = crashSavePath
        setUpExceptionHandler()
    }

    private fun setUpExceptionHandler() {
        if (Thread.getDefaultUncaughtExceptionHandler() !is FunnyCrashExceptionHandler) {
            Thread.setDefaultUncaughtExceptionHandler(FunnyCrashExceptionHandler())
        }
    }

    val context: Context?
        get() {
            if (applicationContext == null) {
                try {
                    throw FunnyCrashNotInitializedException("Initialize FunnyCrash : call FunnyCrash.initialize(context, crashSavePath)")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return applicationContext
        }

//    fun logException(exception: Exception?) {
//        CrashUtil.logException(exception)
//    }

//    val launchIntent: Intent
//        get() = Intent(
//            applicationContext,
//            CrashReporterActivity::class.java
//        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    fun disableNotification() {
        isNotificationEnabled = false
    }
}