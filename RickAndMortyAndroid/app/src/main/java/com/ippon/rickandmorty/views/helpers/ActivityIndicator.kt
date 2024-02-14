package com.ippon.rickandmorty.views.helpers

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ippon.rickandmorty.extension.white

@Composable
fun ActivityIndicator() {
    CircularProgressIndicator(color = Color.white)
}