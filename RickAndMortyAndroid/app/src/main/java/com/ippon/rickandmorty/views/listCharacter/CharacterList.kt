package com.ippon.rickandmorty.views.listCharacter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import com.ippon.rickandmorty.extension.darkGray
import com.ippon.rickandmorty.extension.white
import com.ippon.rickandmorty.models.Filter
import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.Status
import com.ippon.rickandmorty.viewmodels.CharacterViewModel
import com.ippon.rickandmorty.views.filters.GenericFilter
import com.ippon.rickandmorty.views.helpers.ActivityIndicator
import com.ippon.rickandmorty.views.helpers.PageList
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterList(
    onCharacterSelect: (Int) -> Unit
) {
    val characterViewModel: CharacterViewModel = koinViewModel()
    var filteredStatus by remember {
        mutableStateOf(characterViewModel.state.selectedFilter.status)
    }
    var filteredGender by remember {
        mutableStateOf(characterViewModel.state.selectedFilter.gender)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.darkGray),
    ) {
        PageList(
            state = characterViewModel.state,
            itemCard = {
                CharacterCard(character = it, onCharacterSelect = onCharacterSelect)
            },
            listHeader = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    GenericFilter(
                        values = Status.values().toList(),
                        selectedValue = filteredStatus,
                    ) {
                        if (it != filteredStatus) {
                            filteredStatus = it
                            characterViewModel.resetCharacters(
                                Filter(
                                    status = filteredStatus,
                                    gender = filteredGender
                                )
                            )
                        }
                    }
                    GenericFilter(
                        values = Gender.values().toList(),
                        selectedValue = filteredGender,
                    ) {
                        if (it != filteredGender) {
                            filteredGender = it
                            characterViewModel.resetCharacters(
                                Filter(
                                    status = filteredStatus,
                                    gender = filteredGender
                                )
                            )
                        }
                    }
                }
            },
            firstPageLoading = {
                ActivityIndicator()
            },
            firstPageError = {
                CharacterListFirstPageError(it) {
                    characterViewModel.loadCharacters()
                }
            },
            morePageLoading = {
                ActivityIndicator()
            },
            morePageError = {
                CharacterListMorePageError(message = it) {
                    characterViewModel.loadCharacters()
                }
            },
            noMorePage = {
                Text(
                    "No more characters to fetch",
                    color = Color.white,
                    fontStyle = FontStyle.Italic
                )
            }
        ) {
            characterViewModel.loadCharacters()
        }
    }
}