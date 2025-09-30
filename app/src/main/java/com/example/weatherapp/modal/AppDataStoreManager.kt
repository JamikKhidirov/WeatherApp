package com.example.weatherapp.modal

import android.content.Context
import android.text.BoringLayout
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "app-settings")

class AppDataStoreManager(private val context: Context) {

    private val ONBORDING_COMPLETED_KEY = booleanPreferencesKey("onbording_completed")

    //flow который вытаскивает нужно ли показывать экран Onbording
    val isOnboarding: Flow<Boolean> = context.settingsDataStore.data.map { preferences ->
        preferences[ONBORDING_COMPLETED_KEY] ?: false
    }


    //Пользователь впервые зашел в приложение обновляем флажок

    suspend fun markOnbordingAsCompleted(){
        context.settingsDataStore.edit { settings ->
            settings[ONBORDING_COMPLETED_KEY] = true
        }
    }
}