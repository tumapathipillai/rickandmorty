package com.ippon.rickandmorty.views.detailCharacter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ippon.rickandmorty.models.Episode

@Composable
fun EpisodeList(episodes: List<Episode>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            episodes.map {
                EpisodeCard(episode = it)
            }
        }
    }
}