/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash


class FunnyCrashExceptionHandler : Thread.UncaughtExceptionHandler {

    private val exceptionHandler: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        FunnyCrashUtils.saveCrashReport(throwable)
        exceptionHandler?.uncaughtException(thread, throwable)
    }

}