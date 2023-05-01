package com.github.tumusx.easy_permissions.config.routes

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.openConfigByIntent(configAction: String, packageName: String) {
    val intent = Intent().apply {
        action = configAction
        data = Uri.fromParts("package", packageName, null)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}