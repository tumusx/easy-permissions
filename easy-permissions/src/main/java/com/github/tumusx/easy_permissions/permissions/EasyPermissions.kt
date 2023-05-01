package com.github.tumusx.easy_permissions.permissions

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class EasyPermissions(private val activity: AppCompatActivity) {

    fun checkPermissions(permission: String) =
        ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED

    fun checkPermissions(permission: Array<String>) = permission.all { permissionItem ->
        ContextCompat.checkSelfPermission(
            activity,
            permissionItem
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissions(permissions: Array<String>){
        try {
            val requestPermission =
                activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
                    if (isGranted.all { item -> item.value }) {
                        PermissionsListener.initPermissionsListener.onSuccessPermission()
                    } else {
                        PermissionsListener.initPermissionsListener.onRejectPermission()
                    }
                }
            requestPermission.launch(permissions)
        } catch (exception: Exception) {
            exception.printStackTrace()
            PermissionsListener.initPermissionsListener.onErrorPermission()
        }
    }

    fun requestPermissions(permission: String) {
        try {
            val requestPermission =
                activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        PermissionsListener.initPermissionsListener.onSuccessPermission()
                    } else {
                        PermissionsListener.initPermissionsListener.onRejectPermission()
                    }
                }
            requestPermission.launch(permission)
        } catch (exception: Exception) {
            PermissionsListener.initPermissionsListener.onErrorPermission()
            exception.printStackTrace()
        }
    }
}