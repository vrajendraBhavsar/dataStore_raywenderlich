package com.raywenderlich.android.learningcompanion.datastore

import androidx.datastore.preferences.core.*

object PreferencesKey {
    val IS_PIN_SETUP: Preferences.Key<Boolean> = booleanPreferencesKey("is_pin_setup")
    val NAME: Preferences.Key<String> = stringPreferencesKey("name")
    val AGE: Preferences.Key<Int> = intPreferencesKey("age")
    val WEIGHT: Preferences.Key<Float> = floatPreferencesKey("weight")
    val NIGHT_MODE_KEY: Preferences.Key<Boolean> = booleanPreferencesKey("dark_theme_enabled")

}