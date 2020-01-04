/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.mina.funnycrash.FunnyCrashConstants.CRASH_KEY
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer
import java.text.SimpleDateFormat
import java.util.*

object FunnyCrashUtils {

    private val preferencesUtil = FunnyCrachPreferences(FunnyCrash.applicationContext!!)

    fun saveCrashReport(throwable: Throwable) {
        preferencesUtil.saveOrUpdateString(CRASH_KEY, getStackTrace(throwable))
    }

    fun getLastCrashDetails(): String? {
        val lastCrash = preferencesUtil.getString(CRASH_KEY)
        preferencesUtil.delete(CRASH_KEY)
        return lastCrash
    }

    private fun getStackTrace(e: Throwable): String {
        val result: Writer = StringWriter()
        result.write(
            "Crash Data : ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("en")).format(
                Date()
            )} \n\n"
        )
        result.write(getDeviceDetails(FunnyCrash.applicationContext!!) + "\n\n")
        result.write("Stack Trace : \n")
        val printWriter = PrintWriter(result)
        e.printStackTrace(printWriter)
        val crashLog = result.toString()
        printWriter.close()
        return crashLog
    }

    public fun getDeviceDetails(context: Context): String {

        return ("Device Information : \n"
                + "\nVERSION.NAME : " + getAppVersionName(context)
                + "\nVERSION.INCREMENTAL : " + Build.VERSION.INCREMENTAL
                + "\nVERSION.SDK.NUMBER : " + Build.VERSION.SDK_INT
                + "\nBRAND : " + Build.BRAND
                + "\nDISPLAY : " + Build.DISPLAY
                + "\nHARDWARE : " + Build.HARDWARE
                + "\nID : " + Build.ID
                + "\nMANUFACTURER : " + Build.MANUFACTURER
                + "\nMODEL : " + Build.MODEL
                + "\nPRODUCT : " + Build.PRODUCT)
    }

    private fun getAppVersionName(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)

            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException("Could not get package name: $e")
        }
    }
}