package com.ippon.rickandmorty.views.listCharacter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ippon.rickandmorty.extension.black
import com.ippon.rickandmorty.extension.lightGray
import com.ippon.rickandmorty.models.SimpleCharacter

@Composable
fun CharacterCard(character: SimpleCharacter, onCharacterSelect: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.lightGray, shape = RoundedCornerShape(16.dp))
            .padding(8.dp)
            .clickable {
                onCharacterSelect(character.id)
            }
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "${character.name}'s Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                character.name,
                color = Color.black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}