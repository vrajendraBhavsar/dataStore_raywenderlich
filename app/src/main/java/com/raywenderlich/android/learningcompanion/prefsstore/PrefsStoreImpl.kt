package com.raywenderlich.android.learningcompanion.prefsstore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.raywenderlich.android.learningcompanion.di.PREFS_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val STORE_NAME = "mi_data_store"

class PrefsStoreImpl @Inject constructor(@ApplicationContext private val context: Context): PrefsStore {
    //1. Created data store instance
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME, produceMigrations = ::sharedPreferencesMigration)

    private fun sharedPreferencesMigration(context: Context): List<DataMigration<Preferences>> {
        return listOf(SharedPreferencesMigration(context = context, PREFS_NAME))
    }
    private val dataStore: DataStore<Preferences> = context.dataStore

    //Read dataStore data..
    override fun isNightMode(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) {
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }.map { preference ->
            preference[PreferencesKeys.NIGHT_MODE_KEY] ?: false
        }
    }

    //Write datastore data
    override suspend fun toggleNightMode() {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKeys.NIGHT_MODE_KEY] = !(mutablePreferences[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
    }


    //night mode key
    private object PreferencesKeys {
        val NIGHT_MODE_KEY: Preferences.Key<Boolean> = booleanPreferencesKey("dark_theme_enabled")
    }
}