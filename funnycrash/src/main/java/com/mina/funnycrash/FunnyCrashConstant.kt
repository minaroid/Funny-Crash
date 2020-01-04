/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import androidx.annotation.IntDef

object FunnyCrashConstants {

    const val CRASH_KEY: String = "Crash_key"
    const val SCREEN_SHOT_PATH_KEY: String = "Screen_shot_path_key"

    @IntDef(REPORT, CRASH)
    @Retention(AnnotationRetention.SOURCE)
    annotation class ReportType
    const val REPORT = 1
    const val CRASH = 2

}