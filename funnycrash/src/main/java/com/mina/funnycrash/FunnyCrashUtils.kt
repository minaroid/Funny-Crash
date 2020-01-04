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
                + "APP.VERSION : " + getAppVersion(context)
                + "\nVERSION.RELEASE : " + Build.VERSION.RELEASE
                + "\nVERSION.INCREMENTAL : " + Build.VERSION.INCREMENTAL
                + "\nVERSION.SDK.NUMBER : " + Build.VERSION.SDK_INT
                + "\nBOARD : " + Build.BOARD
                + "\nBOOTLOADER : " + Build.BOOTLOADER
                + "\nBRAND : " + Build.BRAND
                + "\nCPU_ABI : " + Build.CPU_ABI
                + "\nCPU_ABI2 : " + Build.CPU_ABI2
                + "\nDISPLAY : " + Build.DISPLAY
                + "\nFINGERPRINT : " + Build.FINGERPRINT
                + "\nHARDWARE : " + Build.HARDWARE
                + "\nHOST : " + Build.HOST
                + "\nID : " + Build.ID
                + "\nMANUFACTURER : " + Build.MANUFACTURER
                + "\nMODEL : " + Build.MODEL
                + "\nPRODUCT : " + Build.PRODUCT
                + "\nTAGS : " + Build.TAGS
                + "\nTIME : " + Build.TIME
                + "\nTYPE : " + Build.TYPE
                + "\nUNKNOWN : " + Build.UNKNOWN
                + "\nUSER : " + Build.USER)
    }

    private fun getAppVersion(context: Context): Int {
        return try {
            val packageInfo = context.packageManager
                .getPackageInfo(context.packageName, 0)
            packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException("Could not get package name: $e")
        }
    }
}