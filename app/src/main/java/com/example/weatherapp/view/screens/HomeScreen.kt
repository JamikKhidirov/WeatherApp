package com.example.weatherapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.view.components.BottomHomeScreen
import com.example.weatherapp.view.components.HomeTabRow
import com.example.weatherapp.view.components.HomeTopAppBar


@Composable
@Preview(
    showSystemUi = true,
    device = PIXEL_7_PRO

)
fun HomeScreen(){

    Scaffold(
        modifier = Modifier.
        fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .background(
                        colorResource(R.color.topAppBarColor)
                    )
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
            modifier = Modifier.padding(top = 5.dp),
            cardModifier = Modifier.alpha(0.9f),
            paddingValues = paddingValues
        )
    }
}