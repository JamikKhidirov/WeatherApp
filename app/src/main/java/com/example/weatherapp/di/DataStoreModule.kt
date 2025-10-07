package com.example.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.weatherapp.modal.cache.AppDataStoreManager
import com.example.weatherapp.modal.cache.settingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //Модулль буде жить за все время приложения
object DataStoreModule {

    @Provides
    @Singleton
    fun providerDataStore(@ApplicationContext context: Context): DataStore<Preferences>{
        return context.settingsDataStore
    }

    @Provides
    @Singleton
    fun provideAppDataStoreManager(dataStore: DataStore<Preferences>): AppDataStoreManager {
        return AppDataStoreManager(dataStore)
    }

}