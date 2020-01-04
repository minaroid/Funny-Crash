/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import java.io.File

data class ReportModel(
    @FunnyCrashConstants.ReportType val reportType: Int, val report: String, val file: File? = null
)