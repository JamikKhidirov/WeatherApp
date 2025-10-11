package com.example.cache.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "app-settings")

@Singleton
class AppDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val ONBORDING_COMPLETED_KEY = booleanPreferencesKey("onbording_completed")

    //flow который вытаскивает нужно ли показывать экран Onbording
    val isOnboarding: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[ONBORDING_COMPLETED_KEY] ?: false
    }


    //Пользователь впервые зашел в приложение обновляем флажок

    suspend fun markOnbordingAsCompleted(){
        dataStore.edit { settings ->
            settings[ONBORDING_COMPLETED_KEY] = true
        }
    }
}