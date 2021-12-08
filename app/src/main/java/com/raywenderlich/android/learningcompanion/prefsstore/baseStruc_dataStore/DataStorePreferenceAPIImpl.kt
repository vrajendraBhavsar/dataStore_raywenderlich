package com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStorePreferenceAPIImpl @Inject constructor(@ApplicationContext context: Context):
    DataStorePreferenceApi {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.STORE_NAME)
    private val dataStore: DataStore<Preferences> = context.dataStore

    override fun<T> getPreference(key : Preferences.Key<T>, defaultValue : T) :
            Flow<T> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val result = preferences[key]?: defaultValue
        result
    }

    override suspend fun<T> putPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun<T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit {
            it.remove(key)
        }
    }

    override suspend fun clearAllPreference() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}