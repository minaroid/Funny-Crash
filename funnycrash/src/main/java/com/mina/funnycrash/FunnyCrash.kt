/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.hardware.SensorManager
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.Gravity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.mina.funnycrash.FunnyCrashConstants.CRASH
import com.mina.funnycrash.FunnyCrashConstants.SCREEN_SHOT_PATH_KEY
import java.io.File
import java.io.FileOutputStream
import java.util.*


object FunnyCrash : LifecycleObserver, Application.ActivityLifecycleCallbacks {

    var applicationContext: Context? = null
    private lateinit var activityContext: Context
    var reportListener: FunnyCrashReportListener? = null
    private lateinit var shakeDetectorListener: ShakeDetector.Listener
    private var shakeDetector: ShakeDetector? = null
    private lateinit var sensorManager: SensorManager
    var showExcuseDialog: Boolean = true
    var shakeForReport = true

    fun initialize(context: Context) {
        applicationContext = context
        setUpExceptionHandler()
        initShakeDetector()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        (applicationContext as Application).registerActivityLifecycleCallbacks(this)

    }

    private fun setUpExceptionHandler() {
        if (Thread.getDefaultUncaughtExceptionHandler() !is FunnyCrashExceptionHandler) {
            Thread.setDefaultUncaughtExceptionHandler(FunnyCrashExceptionHandler())
        }
    }

    private fun initShakeDetector() {
        sensorManager =
            applicationContext?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetectorListener =
            ShakeDetector.Listener {
                reportListener?.let {
                    if (shakeForReport) {
                        takeScreenShot()
                        shakeForReport = false
                    }
                }
            }
        shakeDetector = ShakeDetector(shakeDetectorListener)
        shakeDetector?.start(sensorManager)
    }

    private fun takeScreenShot() {

        val now = Date()
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
        try {

            val mPath: String =
                activityContext.getExternalFilesDir(null)!!.absolutePath.toString() + "/" + now + ".jpg"

            val view = (activityContext as Activity).window.decorView.rootView
            val bitmap: Bitmap =
                Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            val imageFile = File(mPath)
            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()

            val intent = Intent(activityContext, FunnyCrashReportActivity::class.java)
            intent.putExtra(SCREEN_SHOT_PATH_KEY, mPath)
            activityContext.startActivity(intent)

        } catch (e: Throwable) {
            shakeForReport = true
            e.printStackTrace()
        }
    }

    private fun checkLastCrash() {

        reportListener?.let { listener ->
            FunnyCrashUtils.getLastCrashDetails()?.let {
                if (showExcuseDialog) {
                    DialogBuilder(activityContext, R.layout.layout_def_excuse_dialog)
                        .gravity(Gravity.CENTER)
                        .clickListener(R.id.dont_send_btn) { dialog, _ -> dialog.dismiss() }
                        .clickListener(R.id.send_btn) { dialog, _ ->
                            dialog.dismiss()
                            listener.onReceiveReport(it)
                        }
                        .background(R.drawable.inset_bottomsheet_background)
                        .cancelable(false)
                        .build()
                        .show()
                } else {
                    listener.onReceiveReport(it)
                }
            }

        }
    }

    fun crash() {
        val list = listOf(5, 5, 5)
        Log.d("fake index", list[5].toString())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        shakeDetector?.stop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        shakeDetector?.start(sensorManager)
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {
        this.activityContext = activity
        checkLastCrash()
    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityResumed(activity: Activity) {

    }


}