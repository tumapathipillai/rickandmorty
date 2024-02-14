package com.ippon.rickandmorty.views.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ippon.rickandmorty.extension.darkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackScaffold(title: String, navController: NavController, body: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(title)
                },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .background(Color.darkGray)
                .padding(it)
        ) {
            body()
        }
    }
}