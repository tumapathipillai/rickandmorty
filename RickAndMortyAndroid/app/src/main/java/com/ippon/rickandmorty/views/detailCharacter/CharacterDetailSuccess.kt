package com.ippon.rickandmorty.views.detailCharacter

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
import com.ippon.rickandmorty.extension.white
import com.ippon.rickandmorty.models.DetailCharacter

@Composable
fun CharacterDetailSuccess(character: DetailCharacter) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 25.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "${character.name}'s Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(character.name, color = Color.white, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("Gender : ${character.gender.name}", color = Color.white, fontSize = 15.sp)
            Text("Status : ${character.status.name}", color = Color.white, fontSize = 15.sp)
        }
        Text(
            "List of episodes",
            color = Color.white,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        EpisodeList(episodes = character.episodes)
    }
}