package com.ippon.rickandmorty.views.detailCharacter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ippon.rickandmorty.helpers.state.AsyncItemState
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.viewmodels.CharacterViewModel
import com.ippon.rickandmorty.views.helpers.ActivityIndicator
import com.ippon.rickandmorty.views.helpers.BackScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailCharacter(id: Int, navController: NavController) {
    var character by remember {
        mutableStateOf<AsyncItemState<DetailCharacter>>(AsyncItemState.Loading())
    }
    val characterViewModel: CharacterViewModel = koinViewModel()

    BackScaffold(
        title = when (character) {
            is AsyncItemState.Error -> "Error while loading details"
            is AsyncItemState.Loading -> "Loading details..."
            is AsyncItemState.Success -> (character as AsyncItemState.Success<DetailCharacter>).data.name
        }, navController = navController
    ) {
        when (character) {
            is AsyncItemState.Error -> Text((character as AsyncItemState.Error<DetailCharacter>).message)
            is AsyncItemState.Loading -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ActivityIndicator()
            }
            is AsyncItemState.Success -> CharacterDetailSuccess(
                character = (character as AsyncItemState.Success<DetailCharacter>).data
            )
        }
    }

    LaunchedEffect(Unit) {
        character = try {
            AsyncItemState.Success(
                characterViewModel.getCharacterDetail(id)
            )
        } catch (error: Error) {
            AsyncItemState.Error(
                if (error.message != null)
                    error.message!!
                else
                    "An unknown error occurred"
            )
        }
    }
}