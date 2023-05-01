package com.github.tumusx.easy_permissions.config.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val EASY_PREFERENCES = "easy_preferences"

val Context.dataStoreConfigure: DataStore<Preferences> by preferencesDataStore(name = EASY_PREFERENCES)

class EasyPreferences(private val context: Context) {
    private fun <T> getPreferencesKey(type: T, key: String) = when (type) {
        is String -> stringPreferencesKey(key)
        is Int -> intPreferencesKey(key)
        is Float -> floatPreferencesKey(key)
        is Double -> doublePreferencesKey(key)
        is Long -> longPreferencesKey(key)
        is Byte -> byteArrayPreferencesKey(key)
        else -> stringPreferencesKey(key)
    }

    suspend fun <T> readValuePreferences(key: String, type: T): Flow<Any?> {
        return context.dataStoreConfigure.data.map { preferences ->
            preferences[getPreferencesKey(type, key)]
        }
    }

    suspend fun insertValuesPreferences(key: String, value: Any) =
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