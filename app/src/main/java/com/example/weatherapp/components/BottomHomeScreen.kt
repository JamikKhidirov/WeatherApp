package com.example.weatherapp.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.time.timeView.formatDateHeader
import com.example.time.timeView.getSamplemple
import com.example.weatherapp.R

import java.time.LocalDate



@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview()
fun BottomHomeScreen(
    LazyModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(5.dp) // Отступ от Scaffold (TopAppBar)
) {
    val messagesByDate = getSamplemple()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF484B5B),
                        Color(0xFF2C2D35)
                    ),
                    start = Offset(500f, 0f),
                    end = Offset(0f, 1300f)
                )
            )
            .padding(paddingValues), // Применяем отступ СРАЗУ к LazyColumn
        contentPadding = PaddingValues(top = 0.dp) // Обнуляем верхний отступ, так как он уже в paddingValues
    ) {

        // 1. Верхний контент (НЕ липкий)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(), // paddingValues уже применены к LazyColumn
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.dozd),
                    contentDescription = "",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .scale(1.2f)
                )

                Text(
                    text = "33",
                    fontSize = 100.sp,
                    fontWeight = FontWeight(1000),
                    modifier = Modifier.padding(end = 48.dp),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFA2A4B5),
                                Color(0xFF545760)
                            ),
                            start = Offset(100f, 0f),
                            end = Offset(1000f, 0f)
                        )
                    )
                )
            }
        }

        // 2. Липкий заголовок (DateItem)
        messagesByDate.forEach { (date, messages) ->
            // Используем stickyHeader для даты
            stickyHeader(key = "header_${date}") {
                // Оборачиваем DateItem в Row/Box, чтобы обеспечить полное перекрытие при прилипании
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        // Важно: фон stickyHeader должен соответствовать фону экрана
                        .background(Color(0xFF2C2D35))
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DateItem(
                        date = date,
                        modifier = cardModifier
                    )
                }
            }

            // 3. Сообщения, относящиеся к этой дате

        }
    }
}



@Composable
fun DateItem(
    date: LocalDate = LocalDate.now(),
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.cardHeaderColor)
        )
    ) {
        Text(
            text = formatDateHeader(date),
            modifier = Modifier.padding(horizontal = 14.dp,
                vertical = 7.dp),
            color = Color.White
        )
    }
}