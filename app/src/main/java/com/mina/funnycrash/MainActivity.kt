package com.mina.funnycrash

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FunnyCrash.reportListener = object : FunnyCrashReportListener {
            override fun onReceiveRepor(reportModel: ReportModel) {
                Toast.makeText(this@MainActivity, reportModel.report, Toast.LENGTH_SHORT).show()
                if (reportModel.reportType == FunnyCrashConstants.REPORT)
                    screenShot_imageView.setImageBitmap(BitmapFactory.decodeFile(reportModel.file?.absolutePath))
            }
        }

        btn_crash.setOnClickListener {
            FunnyCrash.crash()
//            startActivity(Intent(this, SecondActivity::class.java))
        }


    }


}
