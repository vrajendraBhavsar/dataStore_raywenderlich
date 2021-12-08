package com.raywenderlich.android.learningcompanion.prefsstore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.raywenderlich.android.learningcompanion.di.PREFS_NAME
import com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore.DataStorePreferenceApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val STORE_NAME = "mi_data_store"

class PrefsStoreImpl @Inject constructor(
//    @ApplicationContext context: Context,
    private val dataStorePreferenceApi: DataStorePreferenceApi
    ): PrefsStore {
    //1. Created data store instance
//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME, produceMigrations = ::sharedPreferencesMigration)
//
//    private fun sharedPreferencesMigration(context: Context): List<DataMigration<Preferences>> {
//        return listOf(SharedPreferencesMigration(context = context, PREFS_NAME))
//    }
//    private val dataStore: DataStore<Preferences> = context.dataStore

    //2. Read dataStore data..
    override fun isNightMode(): Flow<Boolean> {
        //1 datastore
//        return dataStore.data.catch { exception ->
//            // dataStore.data throws an IOException if it can't read the data
//            if (exception is IOException) {
//                emit(emptyPreferences())
//            }else{
//                throw exception
//            }
//        }.map { preference ->
//            preference[PreferencesKeys.NIGHT_MODE_KEY] ?: false
//        }

        //.................//2 datastore

//        Log.d("VRAJTEST", "isNightMode() PrefsStoreImpl 37: ${dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY, false).first()}")


            return dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY, false)
        //.................

//        return dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY, false)
//        dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY,"").collect {
//            name = it
//        }
    }

    //3. Write datastore data
    override suspend fun toggleNightMode() {
        Log.d("VRAJTEST", "toggleNightMode PrefStoreImpl called")
        //1 datastore
//        dataStore.edit { mutablePreferences ->
//            mutablePreferences[PreferencesKeys.NIGHT_MODE_KEY] = !(mutablePreferences[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
//        }

        //.................//2 datastore
        val changedDataStoreValue: Boolean = !(dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY, false)).first()
        dataStorePreferenceApi.putPreference(PreferencesKeys.NIGHT_MODE_KEY, !changedDataStoreValue)
        Log.d("VRAJTEST", "changedDataStoreValue PrefStoreImpl 57: $changedDataStoreValue")

        //..................
    }


    //night mode key
    private object PreferencesKeys {
        val NIGHT_MODE_KEY: Preferences.Key<Boolean> = booleanPreferencesKey("dark_theme_enabled")
    }
}