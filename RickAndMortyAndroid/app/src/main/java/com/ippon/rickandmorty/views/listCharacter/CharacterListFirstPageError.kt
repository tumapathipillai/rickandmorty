package com.ippon.rickandmorty.views.listCharacter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ippon.rickandmorty.extension.white

@Composable
fun CharacterListFirstPageError(message: String, onRefreshPress: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            message,
            color = Color.white,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onRefreshPress) {
            Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            Spacer(modifier = Modifier.width(10.dp))
            Text("Try again")
        }
    }
}