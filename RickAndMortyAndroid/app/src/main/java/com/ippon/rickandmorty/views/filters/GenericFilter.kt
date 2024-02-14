package com.ippon.rickandmorty.views.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.ippon.rickandmorty.extension.white

@Composable
fun <T> GenericFilter(
    values: List<T>,
    selectedValue: T?,
    onValueSelected: (T?) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column {
        Text(
            selectedValue.toString(),
            modifier = Modifier.clickable {
                expanded = true
            },
            color = Color.white,
            fontSize = 20.sp
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = {
                    Text(text = "All")
                },
                onClick = {
                    expanded = false
                    onValueSelected(null)
                }
            )
            values.forEach {
                DropdownMenuItem(text = {
                    Text(it.toString())
                }, onClick = {
                    expanded = false
                    onValueSelected(it)
                })
            }
        }
    }
}