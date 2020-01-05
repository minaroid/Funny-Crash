/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mina.funnycrash.FunnyCrashConstants.REPORT
import com.mina.funnycrash.FunnyCrashConstants.SCREEN_SHOT_PATH_KEY
import kotlinx.android.synthetic.main.activity_report.*
import java.io.File
import java.io.StringWriter
import java.io.Writer
import java.text.SimpleDateFormat
import java.util.*

class FunnyCrashReportActivity : AppCompatActivity() {

    private var imagePath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getStringExtra(SCREEN_SHOT_PATH_KEY)?.let {
            imagePath = it
            screenShot_imageView.setImageBitmap(
                BitmapFactory.decodeFile(File(it).absolutePath)
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_report_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_send -> {
                onSendClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onSendClicked() {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("en")).format(Date())

        val report = if (description_editText.text.toString().isEmpty()) "No description entered . "
        else description_editText.text.toString().trim()

        val info = FunnyCrashUtils.getDeviceDetails(FunnyCrash.applicationContext!!)

        FunnyCrash.reportListener?.onReceiveReport(
            ReportModel(REPORT, report, info, date,
                File(imagePath!!)
            )
        )
        finish()
    }

    override fun onDestroy() {
        FunnyCrash.shakeForReport = true
        super.onDestroy()
    }

}