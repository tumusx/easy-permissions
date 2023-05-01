package com.github.tumusx.facilityapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.tumusx.easy_permissions.config.data.EasyPreferences
import com.github.tumusx.easy_permissions.permissions.EasyPermissions
import com.github.tumusx.easy_permissions.permissions.PermissionsListener
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val easyPreferences = EasyPreferences(this@MainActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            easyPreferences.insertValuesPreferences("FIRST_TEST", "FIRST TEST")
            val value = easyPreferences.readValuePreferences("FIRST_TEST", String::class)
            value.collect { testValue ->
                Log.d("FIRST_TEST", testValue as String)
            }
        }

        PermissionsListener.permissionListenerReceive(object : PermissionsListener {
            override fun onSuccessPermission() {
                Log.d("SUCCESS", "SUCCESS")
            }

            override fun onErrorPermission() {
                Log.d("ERROR", "ERROR")
            }

            override fun onRejectPermission() {
                Log.d("REJECTED", "REJECTED")
            }
        })

        EasyPermissions(this@MainActivity).requestPermissions(
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        Log.d("CHECK_PERMISSION",
            EasyPermissions(this@MainActivity).checkPermissions(
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            ).toString()
        )
    }
}