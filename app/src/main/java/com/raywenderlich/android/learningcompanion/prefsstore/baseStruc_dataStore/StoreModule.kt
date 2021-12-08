package com.raywenderlich.android.learningcompanion.prefsstore.baseStruc_dataStore

import com.raywenderlich.android.learningcompanion.prefsstore.PrefsStore
import com.raywenderlich.android.learningcompanion.prefsstore.PrefsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class StoreModule {
//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.STORE_NAME)

//    @Provides
//    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
//        return context.dataStore
//    }

    @Provides
    @Singleton
    fun provideDataStorePreferenceApi(dataStorePreferenceAPIImpl: DataStorePreferenceAPIImpl): DataStorePreferenceApi {
        return dataStorePreferenceAPIImpl
    }

    @Provides
    fun providePrefStore(prefsStoreImpl: PrefsStoreImpl): PrefsStore {
        return prefsStoreImpl
    }
}