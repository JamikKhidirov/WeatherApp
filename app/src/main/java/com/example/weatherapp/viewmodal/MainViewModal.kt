package com.example.weatherapp.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.modal.cache.AppDataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



@HiltViewModel
class MainViewModal @Inject constructor(
    private val dataStoreManager: AppDataStoreManager
): ViewModel() {

    private val _isOnBordingCompleted = MutableStateFlow(false)
    val isOnBordingCompleted: StateFlow<Boolean> = _isOnBordingCompleted.asStateFlow()


    init {
        viewModelScope.launch {
            dataStoreManager.isOnboarding.collect { completed ->
                _isOnBordingCompleted.value = completed
            }
        }
    }

    fun completeOnBording(){
        viewModelScope.launch { dataStoreManager.markOnbordingAsCompleted() }
    }
}