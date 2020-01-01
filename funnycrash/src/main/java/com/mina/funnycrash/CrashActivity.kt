package com.mina.funnycrash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class CrashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_crash.setOnClickListener {

            val list = listOf(5,5,5)
            Log.d("fake index",list[5].toString())

        }
    }

}
