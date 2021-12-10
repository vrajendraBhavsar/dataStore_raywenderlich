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
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.raywenderlich.android.learningcompanion.datastore.DataStoreManager
import com.raywenderlich.android.learningcompanion.datastore.PreferencesKey
import com.raywenderlich.android.learningcompanion.di.PREFS_NAME
import com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore.Constants
import com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore.DataStorePreferenceApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

private const val STORE_NAME = "mi_data_store"

class PrefsStoreImpl @Inject constructor(
//    @ApplicationContext context: Context,
//    private val dataStorePreferenceApi: DataStorePreferenceApi,
    private val dataStoreManager: DataStoreManager
    ): PrefsStore {

    override fun isNightMode(): LiveData<Boolean> {

//            return dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY, false)

        //converting Flow -> Livedata
        val isNightModeLiveData = liveData(Dispatchers.IO) {
            dataStoreManager.getValue(PreferencesKey.NIGHT_MODE_KEY, false).collect {
                emit(it)
            }
        }
        return isNightModeLiveData
    }

    //3. Write datastore data
    override suspend fun toggleNightMode() {
        Log.d("VRAJTEST", "toggleNightMode PrefStoreImpl called")

//        val changedDataStoreValue: Boolean = !(dataStorePreferenceApi.getPreference(PreferencesKeys.NIGHT_MODE_KEY, false)).first()
//        dataStorePreferenceApi.putPreference(PreferencesKeys.NIGHT_MODE_KEY, !changedDataStoreValue)
//        Log.d("VRAJTEST", "changedDataStoreValue PrefStoreImpl 57: $changedDataStoreValue")

        val changedDataStoreValue: Boolean = !(dataStoreManager.getValue(PreferencesKey.NIGHT_MODE_KEY, false).first())
        dataStoreManager.setValue(PreferencesKey.NIGHT_MODE_KEY, changedDataStoreValue)

    }


    //night mode key
//    private object PreferencesKeys {
//        val NIGHT_MODE_KEY: Preferences.Key<Boolean> = booleanPreferencesKey("dark_theme_enabled")
//    }
}