package com.example.weatherapp.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import kotlinx.coroutines.launch



@Preview(showSystemUi = true)
@Composable
fun OnbordingScreen(onLastPageReached: () -> Unit = {}) {
    val pageCount = 4
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    // Прогресс = (текущая страница + 1) / общее количество страниц, с анимацией
    val progress by animateFloatAsState(
        targetValue = ((pagerState.currentPage + 1) / pageCount.toFloat()).coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    // Вызываем коллбек, если дошли до последней страницы
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == pageCount - 1) {
            onLastPageReached()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2D35)).systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 1.dp),
            userScrollEnabled = false // Отключаем свайпы
        ) { page ->
            val imageRes = when (page) {
                0 -> R.drawable.pogoda
                1 -> R.drawable.soon
                2 -> R.drawable.dozd
                3 -> R.drawable.soonoblaka
                else -> null
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(imageRes ?: R.drawable.soon),
                    contentDescription = null,
                    modifier = Modifier.size(270.dp).scale(1.3f)
                        
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { index ->
                val color = if (pagerState.currentPage == index) Color.Black else Color.White
                val width = if (pagerState.currentPage == index) 35.dp else 8.dp
                val currentPage = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .width(width)
                        .height(8.dp)
                        .clip(if (currentPage) CircleShape else RoundedCornerShape(10.dp))
                        .background(color)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 130.dp,
                        topEnd = 130.dp
                    )
                )
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val (title, subtitle) = when (pagerState.currentPage) {
                0 -> "Подробный почасовой\nпрогноз" to "Получите подробную информацию\nо погоде."
                1 -> "Карта погоды в режиме реального времени" to "Следите за выпадением осадков, чтобы быть в курсе событий"
                2 -> "Погода по всему миру" to "Добавьте любое нужное вам местоположение и легко проведите пальцем по нему, чтобы выбрать нужное."
                3 -> "Подробный почасовой прогноз" to "Получите подробную информацию о погоде."
                else -> "" to ""
            }

            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 80.dp)
                    .padding(horizontal = 60.dp)
            )

            Text(
                text = subtitle,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp)
                    .padding(horizontal = 60.dp),
                color = Color(0XFF8B95A2)
            )

            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = progress, // Используем анимированный прогресс
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFC23ACC),
                    strokeWidth = 7.dp
                )
                IconButton(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(80.dp))
                        .background(Color(0xFF2C2D35)),
                    onClick = {
                        val nextPage = (pagerState.currentPage + 1)
                            .coerceAtMost(pageCount - 1)
                        scope.launch {
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.backicon),
                        contentDescription = null,
                        modifier = Modifier.padding(15.dp)
                            .fillMaxSize(),
                        tint = Color.White
                    )
                }
            }
        }
    }
}
