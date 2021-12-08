package com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

class DataStoreProvider @Inject constructor(private val context: Context) {
//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.STORE_NAME)
//
//    fun dataStore(): DataStore<Preferences> = context.dataStore
}