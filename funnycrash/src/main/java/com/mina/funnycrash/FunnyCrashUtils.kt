/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.google.gson.Gson
import com.mina.funnycrash.FunnyCrashConstants.CRASH_KEY
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer
import java.text.SimpleDateFormat
import java.util.*

object FunnyCrashUtils {

    private val preferencesUtil = FunnyCrachPreferences(FunnyCrash.applicationContext!!)
    private var gson = Gson()

    fun saveCrashReport(throwable: Throwable) {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("en")).format(Date())
        val report = getStackTrace(throwable)
        val info = getDeviceDetails(FunnyCrash.applicationContext!!)

        preferencesUtil.saveOrUpdateString(CRASH_KEY, gson.toJson(ReportModel(FunnyCrashConstants.CRASH, report, info, date, null)))
    }

    fun getLastCrashDetails(): ReportModel? {
        val reportModel = gson.fromJson(preferencesUtil.getString(CRASH_KEY),ReportModel::class.java)
        preferencesUtil.delete(CRASH_KEY)
        return reportModel
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

    fun getDeviceDetails(context: Context): String {

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