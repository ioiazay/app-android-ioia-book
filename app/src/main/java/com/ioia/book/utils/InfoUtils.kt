package com.ioia.book.utils

import android.content.Context
import android.content.pm.PackageManager


object InfoUtils {

    fun getAppVersionName(context: Context): String? {
        return try {
            val pm: PackageManager = context.packageManager
            val packageName: String = context.packageName

            val info = pm.getPackageInfo(packageName, 0)
            info.versionName
        } catch (ex: Exception) {
            null
        }
    }

}