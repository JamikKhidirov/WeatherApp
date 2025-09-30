package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.view.screens.OnbordingScreen
import com.example.weatherapp.view.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashScreen = installSplashScreen()

        var keepSplashScreen = true
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        setContent {
            WeatherAppTheme {
                var showSplash by remember { mutableStateOf(true) }
                if (showSplash) {
                    SplashScreen(onTimeout = { showSplash = false })
                } else {
                    OnbordingScreen {

                    }
                }

                // Отмечаем приложение готовым сразу после первого рендера
                LaunchedEffect(Unit) {
                    keepSplashScreen = false
                }
            }
        }
    }
}



