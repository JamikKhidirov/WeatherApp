package com.example.weatherapp.view.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.view.components.HomeTopAppBar


@Composable
@Preview(showSystemUi = true)
fun HomeScreen(){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar(
                modifier = Modifier,
                onClickNavIcon = {},
                onClickActionIcon = {}
            )
        }
    ) {paddingValues ->

    }
}