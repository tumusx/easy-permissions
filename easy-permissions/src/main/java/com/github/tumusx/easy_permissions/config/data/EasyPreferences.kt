package com.github.tumusx.easy_permissions.config.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

const val EASY_PREFERENCES = "easy_preferences"

val Context.dataStoreConfigure: DataStore<Preferences> by preferencesDataStore(name = EASY_PREFERENCES)

class EasyPreferences(private val context: Context) {
    private fun getPreferencesKey(type: Any, key: String) = when (type) {
        is String -> stringPreferencesKey(key)
        is Int -> intPreferencesKey(key)
        is Float -> floatPreferencesKey(key)
        is Double -> doublePreferencesKey(key)
        is Long -> longPreferencesKey(key)
        else -> stringPreferencesKey(key)
    }

    suspend fun readValuePreferences(key: String, type: Any): Flow<Any?> {
        return withContext(Dispatchers.IO) {
            context.dataStoreConfigure.data.map { preferences ->
                preferences[getPreferencesKey(type, key)]
            }
        }
    }

    suspend fun insertValuesPreferences(key: String, value: Any) {
        context.dataStoreConfigure.edit { settings ->
            when (value) {
                is String -> settings[stringPreferencesKey(key)] = value
                is Int -> settings[intPreferencesKey(key)] = value
                is Boolean -> settings[booleanPreferencesKey(key)] = value
                is Float -> settings[floatPreferencesKey(key)] = value
                is Double -> settings[doublePreferencesKey(key)] = value
            }
        }
    }
}