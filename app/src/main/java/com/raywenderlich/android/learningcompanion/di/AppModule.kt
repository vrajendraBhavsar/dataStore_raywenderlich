package com.raywenderlich.android.learningcompanion.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

const val PREFS_NAME = "learning_companion_preferences"

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

  @Provides
  fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}