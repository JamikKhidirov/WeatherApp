package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.screens.HomeScreen
import com.example.weatherapp.screens.OnbordingScreen
import com.example.weatherapp.screens.SplashScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewModal.MainViewModal
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModal by viewModels()

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherAppTheme {
                // Следим за состоянием из ViewModel
                val hasUserSeenOnboarding by viewModel.isOnBordingCompleted.collectAsStateWithLifecycle()

                var showSplashScreen by remember {mutableStateOf(true)}


                // Показываем соответствующий экран
                if (hasUserSeenOnboarding) {
                    if (showSplashScreen){
                        SplashScreen { showSplashScreen = false }
                    }
                    else{
                        HomeScreen()
                    }
                } else {
                    OnbordingScreen {
                        // Когда пользователь завершает онбординг, сохраняем это
                        viewModel.completeOnBording()
                    }
                }
            }
        }
    }
}
