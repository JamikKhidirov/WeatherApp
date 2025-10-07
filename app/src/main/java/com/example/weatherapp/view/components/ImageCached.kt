package com.example.weatherapp.view.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest


@Composable
@Preview(showBackground = true)
fun CahedImage(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    imageUrl: String = ""
){
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = null,
    )

}