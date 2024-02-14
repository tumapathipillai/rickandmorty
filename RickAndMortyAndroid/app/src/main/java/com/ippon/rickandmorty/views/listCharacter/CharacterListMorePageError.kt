package com.ippon.rickandmorty.views.listCharacter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ippon.rickandmorty.extension.lightGray
import com.ippon.rickandmorty.extension.white

@Composable
fun CharacterListMorePageError(message: String, onRefreshPress: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(message, color = Color.white, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = onRefreshPress) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "Refresh",
                tint = Color.white,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.lightGray)
            )
        }
    }
}