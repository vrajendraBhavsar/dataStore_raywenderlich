package com.raywenderlich.android.learningcompanion.prefsstore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.raywenderlich.android.learningcompanion.jagdish_datastore.datastore.DataStoreManager
import com.raywenderlich.android.learningcompanion.jagdish_datastore.datastore.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val STORE_NAME = "mi_data_store"

class PrefsStoreImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : PrefsStore {

    override suspend fun isNightMode(): Flow<Boolean> {
        return dataStoreManager.getValue(PreferencesKey.NIGHT_MODE_KEY,false)
    }

    override suspend fun toggleNightMode(isNightMode : Boolean) {
        dataStoreManager.getValue(PreferencesKey.NIGHT_MODE_KEY, false).collect { storeValue ->
            dataStoreManager.setValue(PreferencesKey.NIGHT_MODE_KEY, isNightMode)   //TOGGLING
        }
    }
}