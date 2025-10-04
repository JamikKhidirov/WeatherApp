package com.example.weatherapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.view.components.HomeTabRow
import com.example.weatherapp.view.components.HomeTopAppBar


@Composable
@Preview(showSystemUi = true)
fun HomeScreen(){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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

    }
}