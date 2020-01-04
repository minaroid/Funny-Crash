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
        val result: Writer = StringWriter()

        result.write(
            "Crash Data : ${SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale("en")).format(Date())} \n\n"
        )

        if (description_editText.text.toString().isEmpty())
            result.write("Description : No description entered .  \n\n")
        else
            result.write("Description :  ${description_editText.text.toString().trim()}  \n\n")

        result.write(FunnyCrashUtils.getDeviceDetails(FunnyCrash.applicationContext!!) + "\n\n")
        result.close()
        FunnyCrash.reportListener?.onReceiveReport(
            ReportModel(
                REPORT, result.toString(),
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