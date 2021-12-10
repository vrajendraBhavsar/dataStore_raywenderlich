package com.raywenderlich.android.learningcompanion.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(Constants.STORE_NAME)

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {

    private val appDataStore = context.dataStore
    private val TAG = "DataStoreManager"

    suspend fun <T> setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        Log.d(TAG, "setValue ---> key: $key -> value: $value")
        appDataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return appDataStore.getValue(key, defaultValue)
    }

    suspend fun <T> DataStore<Preferences>.getValue(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map { preferences ->
            Log.d(TAG, "key -> $key  value -> ${preferences[key]}")
            preferences[key] ?: defaultValue
        }
    }

    suspend fun clearDataStorePreferences() {
        appDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}