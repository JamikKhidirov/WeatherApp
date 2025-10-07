package com.example.weatherapp.view.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = { tabPosition ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart)
                    .tabIndicatorOffset(tabPosition[selectedIndex]),
                color = Color.White,
            )
        },
        modifier = modifier,
        containerColor = colorResource(R.color.topAppBarColor)
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