package com.raywenderlich.android.learningcompanion.prefsstore

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    suspend fun isNightMode() : Flow<Boolean>
    suspend fun toggleNightMode(isNightMode : Boolean)
}