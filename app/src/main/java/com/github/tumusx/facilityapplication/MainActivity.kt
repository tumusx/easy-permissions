package com.github.tumusx.facilityapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.tumusx.easy_permissions.config.data.EasyPreferences
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
    }
}