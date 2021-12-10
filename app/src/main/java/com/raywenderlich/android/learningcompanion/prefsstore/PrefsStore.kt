package com.raywenderlich.android.learningcompanion.prefsstore

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun isNightMode() : LiveData<Boolean>
    suspend fun toggleNightMode()
}