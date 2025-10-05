package com.example.weatherapp.view.components

import android.text.Layout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.modal.data.DataTime
import com.example.weatherapp.modal.data.formatDateHeader
import com.example.weatherapp.modal.data.getSamplemple
import java.time.LocalDate



@Composable
@Preview(showSystemUi = true)
fun BottomHomeScreen(
    LazyModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(10.dp)
){
    val messagesByDate = getSamplemple()
    val listState = rememberLazyListState()

    var currentHeaderIndex = remember {
        derivedStateOf {
            val visibilityItems = listState.layoutInfo.visibleItemsInfo

            //Ищем первый видимый стикихеадер
            visibilityItems.firstOrNull{ itemInfo ->
                itemInfo?.key.toString()?.startsWith("header_") == true
            }?.index ?: 0
        }
    }

    Box(
        modifier = Modifier.then(modifier).fillMaxSize(),
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .then(LazyModifier),
            state = listState
        ) {


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            contentAlignment = Alignment.Center

        ){
            //Создаем список ключей и безопасно получаем элемент по индексу
            val dateKeys = messagesByDate.keys.toList()
            val currentDate = dateKeys.elementAtOrNull(currentHeaderIndex.value)

            //Отображаем DateItem только если дата найдена
            currentDate?.let { date ->
                DateItem(
                    modifier = cardModifier,
                    date = date
                )
            }
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
            containerColor = colorResource(R.color.topAppBarColor)
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