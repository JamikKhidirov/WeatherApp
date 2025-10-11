package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.components.BottomHomeScreen
import com.example.weatherapp.components.HomeTabRow
import com.example.weatherapp.components.HomeTopAppBar



@Composable
@Preview(
    showSystemUi = true,
    device = PIXEL_7_PRO
)
fun HomeScreen(){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
            ){
                HomeTopAppBar(
                    modifier = Modifier,
                    onClickNavIcon = {},
                    onClickActionIcon = {}
                )
                HomeTabRow(modifier = Modifier)
            }
        }
    ) {paddingValues ->
        BottomHomeScreen(
            modifier = Modifier,
            cardModifier = Modifier.alpha(0.9f),
            paddingValues = paddingValues
        )
    }
}