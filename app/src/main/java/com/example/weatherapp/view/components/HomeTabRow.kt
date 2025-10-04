package com.example.weatherapp.view.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


@Composable
@Preview(showBackground = true)
fun HomeTabRow(
    modifier: Modifier = Modifier,
){
    val tabList: List<String> = listOf("Сегодня", "Прогноз", "Осадок")
    var selectedIndex by remember { mutableStateOf(0) }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = colorResource(R.color.topAppBarColor),
        indicator = {tabPosition ->

            val currentTabPosition = tabPosition[selectedIndex]
            //Анимируем сдвиг индикатора
            var indicatorOffset = animateDpAsState(
                targetValue =  currentTabPosition.left
            )

            val currentPossitinLeft = currentTabPosition.left

            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart)
                    .width(currentTabPosition.width)
                    .offset(x = indicatorOffset.value),
                color = Color.White,
            )
        }
    ) {
        tabList.forEachIndexed { index, string ->
            Tab(
                selected = selectedIndex ==  index,
                onClick = {
                    selectedIndex = index
                },
                text = {
                    Text(
                        text = string,
                        color = Color.White
                    )
                },
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }

}