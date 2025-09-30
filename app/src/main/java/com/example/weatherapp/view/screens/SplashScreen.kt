package com.example.weatherapp.view.screens


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7A
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(onTimeout: () -> Unit = {}) {

    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    var paddingState by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val startPadding = 0.dp
    val endPadding = 25.dp

    val animatedPadding = animateDpAsState(
        targetValue = if (paddingState) endPadding else startPadding,
        animationSpec = tween(1000)
    )

    LaunchedEffect(true) {
        // Запускаем анимации параллельно
        scope.launch {
            scale.animateTo(
                targetValue = 1.05f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
        scope.launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
        scope.launch {
            delay(1000) // Немного подождать, чтобы паддинг запускался именно после масштабирования стартует плавно
            paddingState = true
        }
        delay(2100) // Общее время анимации + паддинг (1с + 1с + небольшой запас)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0.0f to Color(0xffBCC8D6),
                    0.59f to Color(0xFFD2DAE4),
                    1.0f to Color(0xFFF2F4F7),
                    start = Offset(1000f, 1f),
                    end = Offset(1000f, 3000f)
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.splashicon),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(300.dp)
                    .scale(scale.value)
            )

            Column(
                modifier = Modifier.padding(top = animatedPadding.value),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Weather",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.alpha(alpha.value)
                )
                Text(
                    text = "Forecast",
                    fontSize = 25.sp,
                    color = Color(0xFF8B95A2),
                    modifier = Modifier.alpha(alpha.value)
                )
            }
        }
    }
}



