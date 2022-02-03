package com.example.myappnotes.elements.uiElements

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//ToppAppBar um die Überschrift anzuzeigen

@Composable
fun TopBarElement(

) {
    TopAppBar {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Row(modifier= Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment= Alignment.CenterVertically) {
                Text(
                    text = "My Notes",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.secondary
                )

            }

        }

    }
}